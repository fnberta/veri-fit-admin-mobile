package ch.berta.fabio.verifitadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.core.auth.AuthService
import ch.berta.fabio.verifitadmin.core.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(authService: AuthService) : ViewModel() {
    val uiState = authService.getAuthState()
        .map { AppUiState(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            AppUiState(AuthState.LoggedOut)
        )
}

data class AppUiState(val authState: AuthState)