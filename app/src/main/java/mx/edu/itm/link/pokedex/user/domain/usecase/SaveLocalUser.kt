package mx.edu.itm.link.pokedex.user.domain.usecase

import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.user.domain.repository.LocalUserRepository

class SaveLocalUser(
    private val repo: LocalUserRepository
) {
    suspend operator fun invoke(data:User) = repo.insertUser(user = data)
}