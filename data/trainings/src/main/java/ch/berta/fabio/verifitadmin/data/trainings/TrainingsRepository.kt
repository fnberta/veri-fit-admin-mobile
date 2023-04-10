package ch.berta.fabio.verifitadmin.data.trainings

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TrainingsRepository {
    fun getTrainings(): Flow<List<Training>>
}

internal class SimpleTrainingsRepository @Inject constructor() : TrainingsRepository {
    override fun getTrainings(): Flow<List<Training>> = flow {
        delay(1000)
        emit(emptyList())
    }
}