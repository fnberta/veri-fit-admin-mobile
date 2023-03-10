package ch.berta.fabio.verifitadmin.feature.clients

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

@Composable
internal fun Clients(uiState: ClientsUiState) {
    when (uiState) {
        ClientsUiState.Loading -> Text("Loading...")
        ClientsUiState.Error -> Text("Oops...")
        is ClientsUiState.Content -> {
            LazyColumn {
                items(items = uiState.clients, key = { it.id.toString() }) { client ->
                    Text(client.name.toString())
                }
            }
        }
    }
}

const val CLIENTS_ROUTE = "clients"

@OptIn(ExperimentalLifecycleComposeApi::class)
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

@Preview
@Composable
private fun ClientsPreview() {
    Clients(ClientsUiState.Loading)
}