package ch.berta.fabio.verifitadmin.feature.trainings

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
internal fun Trainings(uiState: TrainingsUiState) {
    when (uiState) {
        TrainingsUiState.Loading -> Text("Loading...")
        TrainingsUiState.Error -> Text("Oops...")
        is TrainingsUiState.Content -> Text("Hello Trainings")
    }
}

const val TRAININGS_ROUTE = "trainings"

@OptIn(ExperimentalLifecycleComposeApi::class)
fun NavGraphBuilder.trainings() {
    composable(TRAININGS_ROUTE) {
        val viewModel = hiltViewModel<TrainingsViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        Trainings(uiState)
    }
}

fun NavController.navigateToTrainings() {
    navigate(TRAININGS_ROUTE)
}

@Preview
@Composable
private fun TrainingsPreview() {
    Trainings(TrainingsUiState.Content(trainings = emptyList()))
}