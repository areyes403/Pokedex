package mx.edu.itm.link.pokedex.user.domain.usecase

import mx.edu.itm.link.pokedex.user.domain.repository.local.LocalUserRepository

class GetLocalUser(
    private val repo: LocalUserRepository
){
    suspend operator fun invoke() = repo.getLocalUser()
}