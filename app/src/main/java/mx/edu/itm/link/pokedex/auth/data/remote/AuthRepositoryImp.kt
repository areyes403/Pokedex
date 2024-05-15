package mx.edu.itm.link.pokedex.auth.data.remote

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.auth.domain.repository.AuthRepository
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.FirestoreCollections

class AuthRepositoryImp (
    private var auth:FirebaseAuth,
    private var firestore:FirebaseFirestore
):AuthRepository {

    override suspend fun signUp(credential: Credentials, user: User): ResponseStatus<String> =try {
        val uid=auth.createUserWithEmailAndPassword(credential.email,credential.password)
            .await()
            .user?.uid

        firestore.collection(FirestoreCollections.USER)
            .document(uid.toString())
            .set(user)
            .await()

        ResponseStatus.Success("")
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun signIn(credential: Credentials): ResponseStatus<String> = try {
        auth.signInWithEmailAndPassword(credential.email,credential.password).await()
        ResponseStatus.Success("")

    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }
}