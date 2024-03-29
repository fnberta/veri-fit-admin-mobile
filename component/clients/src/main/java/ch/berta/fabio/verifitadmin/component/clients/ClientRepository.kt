package ch.berta.fabio.verifitadmin.component.clients

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface ClientRepository {
    fun getAll(): Flow<List<Client>>
}

internal class FirebaseClientRepository @Inject constructor(private val db: FirebaseFirestore) :
    ClientRepository {

    override fun getAll(): Flow<List<Client>> =
        db.collection("clients").snapshots().map {
            it.toObjects<DbClient>().map(DbClient::toClient)
        }
}

private data class DbClient(@DocumentId val id: String = "", val name: String = "")

private fun DbClient.toClient(): Client = Client(id = ClientId(id), name = ClientName(name))
