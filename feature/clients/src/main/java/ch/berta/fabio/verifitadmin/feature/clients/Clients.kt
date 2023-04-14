package ch.berta.fabio.verifitadmin.feature.clients

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ch.berta.fabio.core.theme.Theme

@Composable
internal fun Clients(uiState: ClientsUiState) {
    when (uiState) {
        ClientsUiState.Loading -> Text("Loading...")
        is ClientsUiState.Content -> {
            LazyColumn {
                items(items = uiState.clients, key = { it.id.toString() }) {
                    Text(it.name.toString())
                }
            }
        }
    }
}

const val CLIENTS_ROUTE = "clients"

fun NavGraphBuilder.clients() {
    composable(CLIENTS_ROUTE) {
        val clientsViewModel = hiltViewModel<ClientsViewModel>()
        val uiState by clientsViewModel.uiState.collectAsStateWithLifecycle()
        Clients(uiState)
    }
}

fun NavController.navigateToClients() {
    navigate(CLIENTS_ROUTE)
}

@Preview(showBackground = true)
@Composable
private fun ClientsPreview(
    @PreviewParameter(ClientsUiStateProvider::class) uiState: ClientsUiState,
) {
    Theme { Clients(uiState) }
}

private class ClientsUiStateProvider : PreviewParameterProvider<ClientsUiState> {
    override val values =
        sequenceOf(
            ClientsUiState.Loading,
            ClientsUiState.Content(clients = emptyList()),
        )
}
