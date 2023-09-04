package ch.berta.fabio.verifitadmin.component.auth

import android.app.PendingIntent
import android.content.Intent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

private const val SERVER_CLIENT_ID =
    "955308028827-sequkajgsca3e4hv3rndpqsme40rpa3h.apps.googleusercontent.com"

interface AuthClient {
    val authState: Flow<AuthState>

    suspend fun getSignInIntent(): PendingIntent

    suspend fun processSignInData(data: Intent?)
}

internal class FirebaseAuthClient
@Inject
constructor(private val auth: FirebaseAuth, private val oneTapClient: SignInClient) : AuthClient {

    override val authState: Flow<AuthState> =
        auth.authState.map { currentUser ->
            if (currentUser != null) AuthState.LoggedIn(UserId(currentUser.uid))
            else AuthState.LoggedOut
        }

    override suspend fun getSignInIntent(): PendingIntent {
        val signInRequest =
            BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(SERVER_CLIENT_ID)
                        .setFilterByAuthorizedAccounts(true)
                        .build()
                )
                .setAutoSelectEnabled(true)
                .build()
        val result = oneTapClient.beginSignIn(signInRequest).await()
        return result.pendingIntent
    }

    override suspend fun processSignInData(data: Intent?) {
        val credentials = oneTapClient.getSignInCredentialFromIntent(data)
        val idToken = credentials.googleIdToken
        checkNotNull(idToken)
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(firebaseCredential).await()
    }
}

private val FirebaseAuth.authState: Flow<FirebaseUser?>
    get() = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser) }
        addAuthStateListener(listener)
        awaitClose { removeAuthStateListener(listener) }
    }
