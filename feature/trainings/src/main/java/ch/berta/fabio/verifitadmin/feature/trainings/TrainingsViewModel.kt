package ch.berta.fabio.verifitadmin.feature.trainings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.berta.fabio.verifitadmin.component.trainings.GetTrainings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
internal class TrainingsViewModel @Inject constructor(getTrainings: GetTrainings) : ViewModel() {
    val uiState =
        getTrainings()
            .map<_, TrainingsUiState> { TrainingsUiState.Content(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = TrainingsUiState.Loading
            )
}
