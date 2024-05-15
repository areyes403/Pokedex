package mx.edu.itm.link.pokedex.auth.domain.usecase

import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.repository.AuthRepository

class SignIn (
    private val repository: AuthRepository
){
    suspend operator fun invoke(cred:Credentials) = repository.signIn(credential = cred)
}