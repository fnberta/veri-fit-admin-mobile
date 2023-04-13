package ch.berta.fabio.verifitadmin.data.trainings

import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TrainingRepository {
    fun getTrainings(): Flow<List<Training>>
}

internal class SimpleTrainingRepository @Inject constructor() : TrainingRepository {
    override fun getTrainings(): Flow<List<Training>> = flow {
        delay(1000)
        emit(emptyList())
    }
}
