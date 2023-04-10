package ch.berta.fabio.verifitadmin.feature.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.data.clients.Client
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal sealed interface ClientsUiState {
    object Loading : ClientsUiState
    object Error : ClientsUiState
    data class Content(val clients: List<Client>) : ClientsUiState
}

@HiltViewModel
internal class ClientsViewModel @Inject constructor(getClients: GetClients) : ViewModel() {
    val uiState: StateFlow<ClientsUiState> = getClients()
        .map<_, ClientsUiState> { ClientsUiState.Content(it) }
        .catch { emit(ClientsUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ClientsUiState.Loading
        )
}