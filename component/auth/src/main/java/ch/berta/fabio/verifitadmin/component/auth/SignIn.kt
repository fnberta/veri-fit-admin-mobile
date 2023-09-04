package ch.berta.fabio.verifitadmin.component.auth

import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import javax.inject.Inject

fun interface LaunchSignIn {
    suspend operator fun invoke(launch: (request: IntentSenderRequest) -> Unit)
}

internal class DefaultLaunchSignIn @Inject constructor(private val authClient: AuthClient) :
    LaunchSignIn {

    override suspend fun invoke(launch: (request: IntentSenderRequest) -> Unit) {
        val request = IntentSenderRequest.Builder(authClient.getSignInIntent()).build()
        launch(request)
    }
}

fun interface ProcessSignIn {
    suspend operator fun invoke(data: Intent?)
}

internal class DefaultProcessSignIn @Inject constructor(private val authClient: AuthClient) :
    ProcessSignIn {

    override suspend fun invoke(data: Intent?) = authClient.processSignInData(data)
}
