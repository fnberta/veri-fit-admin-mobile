package ch.berta.fabio.verifitadmin.feature.trainings

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
internal fun Trainings(uiState: TrainingsUiState) {
    when (uiState) {
        TrainingsUiState.Loading -> Text("Loading...")
        is TrainingsUiState.Content -> Text("Hello Trainings")
    }
}

const val TrainingsRoute = "trainings"

fun NavGraphBuilder.trainings() {
    composable(TrainingsRoute) {
        val viewModel = hiltViewModel<TrainingsViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        Trainings(uiState)
    }
}

fun NavController.navigateToTrainings() {
    navigate(TrainingsRoute)
}

@Preview(showBackground = true)
@Composable
private fun TrainingsPreview(
    @PreviewParameter(TrainingsUiStatePreviewProvider::class) uiState: TrainingsUiState,
) {
    Theme { Trainings(uiState) }
}

private class TrainingsUiStatePreviewProvider : PreviewParameterProvider<TrainingsUiState> {
    override val values =
        sequenceOf(
            TrainingsUiState.Loading,
            TrainingsUiState.Content(trainings = emptyList()),
        )
}
