package ch.berta.fabio.verifitadmin.component.auth

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

fun interface ObserveAuthState {
    operator fun invoke(): Flow<AuthState>
}

sealed interface AuthState {
    data object LoggedOut : AuthState

    data class LoggedIn(val userId: UserId) : AuthState
}

@JvmInline value class UserId(val id: String)

internal class DefaultObserveAuthState @Inject constructor(private val authClient: AuthClient) :
    ObserveAuthState {
    override operator fun invoke(): Flow<AuthState> = authClient.authState
}
