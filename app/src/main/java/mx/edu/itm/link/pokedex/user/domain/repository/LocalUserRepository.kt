package mx.edu.itm.link.pokedex.user.domain.repository

import mx.edu.itm.link.pokedex.core.domain.model.User


interface LocalUserRepository {
    suspend fun getLocalUser(): User?
    suspend fun insertUser(user:User)
}