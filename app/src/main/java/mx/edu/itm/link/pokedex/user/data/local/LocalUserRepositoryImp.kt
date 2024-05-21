package mx.edu.itm.link.pokedex.user.data.local

import mx.edu.itm.link.pokedex.core.data.room.DatabaseManager
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.util.toLocalUserEntity
import mx.edu.itm.link.pokedex.core.util.toUser
import mx.edu.itm.link.pokedex.user.domain.repository.local.LocalUserRepository

class LocalUserRepositoryImp: LocalUserRepository {

    private val localUser=DatabaseManager.instance.database.localUserDao()

    override suspend fun getLocalUser(): User? = if (localUser.getUser().isNotEmpty()){
        localUser.getUser()[0].toUser()
    } else null

    override suspend fun insertUser(user: User) {
        localUser.insertUser(user = user.toLocalUserEntity())
    }

    override suspend fun deleteUser() {
        localUser.deleteAll()
    }

}