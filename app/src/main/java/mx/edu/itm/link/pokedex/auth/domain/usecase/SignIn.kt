package mx.edu.itm.link.pokedex.auth.domain.usecase

import mx.edu.itm.link.pokedex.auth.data.repository.FirestoreRepositoyImp
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.repository.AuthRepository
import mx.edu.itm.link.pokedex.auth.domain.repository.FirestoreRepository
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp

class SignIn (
    private val authRepo: AuthRepository,
    private val firestoreRepo:FirestoreRepository
){
    suspend operator fun invoke(data:Credentials): ResponseStatus<Unit> {
        return when (val authResult = authRepo.signIn(credential = data)) {
            is ResponseStatus.Loading -> ResponseStatus.Loading
            is ResponseStatus.Error -> ResponseStatus.Error(authResult.error)
            is ResponseStatus.Success -> {
                val userResult = firestoreRepo.getUser(uid = authResult.data)
                if (userResult is ResponseStatus.Success) {
                    val localrep= LocalUserRepositoryImp()
                    localrep.insertUser(userResult.data)
                    ResponseStatus.Success(Unit)
                } else {
                    ResponseStatus.Error("Error al obtener datos del usuario")
                }
            }
        }
    }
}