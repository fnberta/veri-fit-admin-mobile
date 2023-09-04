package ch.berta.fabio.verifitadmin.feature.trainings

import ch.berta.fabio.verifitadmin.component.trainings.Training

internal sealed class TrainingsUiState {
    data object Loading : TrainingsUiState()

    data class Content(val trainings: List<Training>) : TrainingsUiState()
}
