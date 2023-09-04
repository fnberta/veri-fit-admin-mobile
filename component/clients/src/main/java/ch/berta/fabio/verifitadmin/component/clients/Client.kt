package ch.berta.fabio.verifitadmin.component.clients

data class Client(val id: ClientId, val name: ClientName)

@JvmInline value class ClientId(val id: String)

@JvmInline value class ClientName(val name: String)
