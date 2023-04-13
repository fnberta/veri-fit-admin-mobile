package ch.berta.fabio.verifitadmin.feature.clients

import ch.berta.fabio.verifitadmin.data.clients.Client

internal sealed interface ClientsUiState {
    object Loading : ClientsUiState
    data class Content(val clients: List<Client>) : ClientsUiState
}
