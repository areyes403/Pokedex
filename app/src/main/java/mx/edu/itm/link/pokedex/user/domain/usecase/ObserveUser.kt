package mx.edu.itm.link.pokedex.user.domain.usecase

import mx.edu.itm.link.pokedex.user.data.remote.UserRepositoryImp

class ObserveUser(
    private val repo:UserRepositoryImp
) {
    suspend operator fun invoke(id:String) = repo.getUser(uid = id)
}