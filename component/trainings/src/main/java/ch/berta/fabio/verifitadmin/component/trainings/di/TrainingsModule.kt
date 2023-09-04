package ch.berta.fabio.verifitadmin.component.trainings.di

import ch.berta.fabio.verifitadmin.component.trainings.DefaultGetTrainings
import ch.berta.fabio.verifitadmin.component.trainings.GetTrainings
import ch.berta.fabio.verifitadmin.component.trainings.SimpleTrainingRepository
import ch.berta.fabio.verifitadmin.component.trainings.TrainingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface TrainingsModule {

    @Singleton
    @Binds
    fun bindTrainingRepository(repository: SimpleTrainingRepository): TrainingRepository

    @Binds fun bindGetTrainings(getTrainings: DefaultGetTrainings): GetTrainings
}
