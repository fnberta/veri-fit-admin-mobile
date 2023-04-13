package ch.berta.fabio.verifitadmin.data.clients

data class Client(val id: ClientId, val name: ClientName)

@JvmInline value class ClientId(val id: String)

@JvmInline value class ClientName(val name: String)
