package ch.berta.fabio.verifitadmin.feature.trainings

import ch.berta.fabio.verifitadmin.data.trainings.Training
import ch.berta.fabio.verifitadmin.data.trainings.TrainingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetTrainings @Inject constructor(private val trainingsRepository: TrainingsRepository) {

    operator fun invoke(): Flow<List<Training>> = trainingsRepository.getTrainings()
}