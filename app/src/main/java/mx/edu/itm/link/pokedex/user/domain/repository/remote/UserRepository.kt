package mx.edu.itm.link.pokedex.user.domain.repository.remote

import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User


interface UserRepository {
    suspend fun getUser(uid:String): Flow<ResponseStatus<User>>
}