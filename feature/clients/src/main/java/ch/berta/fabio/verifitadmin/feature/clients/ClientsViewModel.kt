package ch.berta.fabio.verifitadmin.feature.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.component.clients.GetClients
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
internal class ClientsViewModel @Inject constructor(getClients: GetClients) : ViewModel() {
    val uiState: StateFlow<ClientsUiState> =
        getClients()
            .map<_, ClientsUiState> { ClientsUiState.Content(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ClientsUiState.Loading
            )
}
