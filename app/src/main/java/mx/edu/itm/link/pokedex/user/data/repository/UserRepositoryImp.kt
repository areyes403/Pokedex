package mx.edu.itm.link.pokedex.user.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.util.FirestoreCollections
import mx.edu.itm.link.pokedex.user.domain.repository.remote.UserRepository

class UserRepositoryImp(
    private val firestore:FirebaseFirestore
):UserRepository {
    override suspend fun getUser(uid: String): Flow<ResponseStatus<User>> = firestore.collection(FirestoreCollections.USER)
        .document(uid)
        .snapshots()
        .mapNotNull {
            ResponseStatus.Success(it.toObject(User::class.java)!!)
        }.catch {
            ResponseStatus.Error(it.localizedMessage)
        }

}