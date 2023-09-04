package ch.berta.fabio.verifitadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.component.auth.AuthState
import ch.berta.fabio.verifitadmin.component.auth.ObserveAuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class AppViewModel @Inject constructor(observeAuthState: ObserveAuthState) : ViewModel() {
    val uiState =
        observeAuthState()
            .map { AppUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AppUiState(AuthState.LoggedOut)
            )
}

data class AppUiState(val authState: AuthState)
