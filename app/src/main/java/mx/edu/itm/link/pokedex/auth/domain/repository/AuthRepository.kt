package mx.edu.itm.link.pokedex.auth.domain.repository

import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus

interface AuthRepository {
    suspend fun signUp(credential:Credentials):ResponseStatus<String>
    suspend fun signIn(credential:Credentials):ResponseStatus<String>
}