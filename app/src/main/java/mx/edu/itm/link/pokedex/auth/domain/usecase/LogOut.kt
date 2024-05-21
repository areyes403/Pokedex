package mx.edu.itm.link.pokedex.auth.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.repository.local.LocalPokemonRepository
import mx.edu.itm.link.pokedex.user.domain.repository.local.LocalUserRepository

class LogOut(
    private val localUserRepo:LocalUserRepository,
    private val localPokemonRepo:LocalPokemonRepository
) {
    suspend operator fun invoke(){
        localUserRepo.deleteUser()
        localPokemonRepo.deletePokemons()
    }
}