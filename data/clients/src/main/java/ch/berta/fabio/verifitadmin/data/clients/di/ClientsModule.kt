package ch.berta.fabio.verifitadmin.data.clients.di

import ch.berta.fabio.verifitadmin.data.clients.ClientRepository
import ch.berta.fabio.verifitadmin.data.clients.FirebaseClientRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ClientsModule {

    @Singleton
    @Binds
    fun bindsClientRepository(repository: FirebaseClientRepository): ClientRepository
}