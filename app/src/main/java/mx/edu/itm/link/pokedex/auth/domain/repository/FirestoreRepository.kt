package mx.edu.itm.link.pokedex.auth.domain.repository

import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User

interface FirestoreRepository {
    suspend fun saveUser(uid:String,user: User):ResponseStatus<User>
    suspend fun getUser(uid: String):ResponseStatus<User>
}