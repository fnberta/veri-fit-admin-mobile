package ch.berta.fabio.verifitadmin.feature.clients

import ch.berta.fabio.verifitadmin.data.clients.Client
import ch.berta.fabio.verifitadmin.data.clients.ClientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetClients @Inject constructor(private val clientRepository: ClientRepository) {

    operator fun invoke(): Flow<List<Client>> = clientRepository.getClients()
}