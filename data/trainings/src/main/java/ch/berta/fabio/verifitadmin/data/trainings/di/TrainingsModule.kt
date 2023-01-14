package ch.berta.fabio.verifitadmin.data.trainings.di

import ch.berta.fabio.verifitadmin.data.trainings.SimpleTrainingsRepository
import ch.berta.fabio.verifitadmin.data.trainings.TrainingsRepository
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
    fun bindsTrainingsRepository(repository: SimpleTrainingsRepository): TrainingsRepository
}