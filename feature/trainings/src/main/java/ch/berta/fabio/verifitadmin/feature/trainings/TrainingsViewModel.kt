package ch.berta.fabio.verifitadmin.feature.trainings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.data.trainings.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class TrainingsViewModel @Inject constructor(
    trainingRepository: TrainingRepository
) : ViewModel() {
    val uiState = trainingRepository.getTrainings()
        .map<_, TrainingsUiState> { TrainingsUiState.Content(it) }
        .catch { emit(TrainingsUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TrainingsUiState.Loading
        )
}