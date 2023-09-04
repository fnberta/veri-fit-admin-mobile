package ch.berta.fabio.verifitadmin.component.clients.di

import ch.berta.fabio.verifitadmin.component.clients.ClientRepository
import ch.berta.fabio.verifitadmin.component.clients.DefaultGetClients
import ch.berta.fabio.verifitadmin.component.clients.FirebaseClientRepository
import ch.berta.fabio.verifitadmin.component.clients.GetClients
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
    fun bindClientRepository(repository: FirebaseClientRepository): ClientRepository

    @Binds fun bindGetClients(getClients: DefaultGetClients): GetClients
}
