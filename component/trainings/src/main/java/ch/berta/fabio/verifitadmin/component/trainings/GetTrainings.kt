package ch.berta.fabio.verifitadmin.component.trainings

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

fun interface GetTrainings {
    operator fun invoke(): Flow<List<Training>>
}

internal class DefaultGetTrainings
@Inject
constructor(private val trainingRepository: TrainingRepository) : GetTrainings {
    override operator fun invoke(): Flow<List<Training>> = trainingRepository.getAll()
}
