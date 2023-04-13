package ch.berta.fabio.verifitadmin.feature.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.data.clients.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*

@HiltViewModel
internal class ClientsViewModel @Inject constructor(clientRepository: ClientRepository) :
    ViewModel() {
    val uiState: StateFlow<ClientsUiState> =
        clientRepository
            .getClients()
            .map<_, ClientsUiState> { ClientsUiState.Content(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ClientsUiState.Loading
            )
}
