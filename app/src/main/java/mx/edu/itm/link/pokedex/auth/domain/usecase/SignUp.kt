package mx.edu.itm.link.pokedex.auth.domain.usecase

import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.auth.domain.repository.AuthRepository
import mx.edu.itm.link.pokedex.auth.domain.repository.FirestoreRepository
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp

class SignUp(
    private val authRepo: AuthRepository,
    private val firestoreRepo:FirestoreRepository
) {
    suspend operator fun invoke(cred:Credentials,usr: User): ResponseStatus<Unit>{
        return when(val authResult=authRepo.signUp(credential = cred)){
            is ResponseStatus.Loading-> return ResponseStatus.Loading
            is ResponseStatus.Error-> return ResponseStatus.Error(authResult.error)
            is ResponseStatus.Success->{
                val userResult=firestoreRepo.saveUser(authResult.data,usr)
                if (userResult is ResponseStatus.Success) {
                    ResponseStatus.Success(Unit)
                } else {
                    ResponseStatus.Error("Error al obtener datos del usuario")
                }
            }
        }
    }
}