package ch.berta.fabio.verifitadmin.component.trainings

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal interface TrainingRepository {
    fun getAll(): Flow<List<Training>>
}

internal class SimpleTrainingRepository @Inject constructor() : TrainingRepository {
    override fun getAll(): Flow<List<Training>> = flow {
        delay(1.seconds)
        emit(emptyList())
    }
}
