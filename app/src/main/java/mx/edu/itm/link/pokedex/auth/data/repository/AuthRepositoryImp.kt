package mx.edu.itm.link.pokedex.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.repository.AuthRepository
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus

class AuthRepositoryImp (
    private var auth:FirebaseAuth
):AuthRepository {

    override suspend fun signUp(credential: Credentials): ResponseStatus<String> =try {
        val uid=auth.createUserWithEmailAndPassword(credential.email,credential.password)
            .await()
            .user?.uid!!

        ResponseStatus.Success(uid)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun signIn(credential: Credentials): ResponseStatus<String> = try {
        val uid = auth.signInWithEmailAndPassword(credential.email,credential.password)
            .await()
            .user?.uid!!

        ResponseStatus.Success(uid)

    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }
}