package ch.berta.fabio.verifitadmin.feature.trainings

import ch.berta.fabio.verifitadmin.data.trainings.Training

internal sealed class TrainingsUiState {
    object Loading : TrainingsUiState()
    data class Content(val trainings: List<Training>) : TrainingsUiState()
}
