package mx.edu.itm.link.pokedex.auth.domain.usecase

import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.auth.domain.repository.AuthRepository

class SignUp(
    private val repository: AuthRepository
) {
    suspend operator fun  invoke(cred:Credentials,usr: User) = repository.signUp(credential = cred, user = usr)
}