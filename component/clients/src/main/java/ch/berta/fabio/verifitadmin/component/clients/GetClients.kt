package ch.berta.fabio.verifitadmin.component.clients

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

fun interface GetClients {
    operator fun invoke(): Flow<List<Client>>
}

internal class DefaultGetClients
@Inject
constructor(private val clientRepository: ClientRepository) : GetClients {
    override operator fun invoke(): Flow<List<Client>> = clientRepository.getAll()
}
