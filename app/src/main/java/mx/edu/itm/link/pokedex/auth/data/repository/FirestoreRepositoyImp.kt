package mx.edu.itm.link.pokedex.auth.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.pokedex.auth.domain.repository.FirestoreRepository
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.util.FirestoreCollections

class FirestoreRepositoyImp(
    private val firestore:FirebaseFirestore
) :FirestoreRepository{
    override suspend fun saveUser(uid: String, user: User): ResponseStatus<User> = try {
        firestore.collection(FirestoreCollections.USER)
            .document(uid)
            .set(user)
            .await()

        ResponseStatus.Success(user)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun getUser(uid: String): ResponseStatus<User> {
        TODO("Not yet implemented")
    }
}