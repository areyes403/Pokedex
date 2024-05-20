package mx.edu.itm.link.pokedex.pokemon.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.repository.local.LocalPokemonRepository

class GetLocalPokemonsByUserId(
    private val repo:LocalPokemonRepository
) {
    suspend operator fun invoke(uid:String)=repo.getPokemonsByUserId(userId = uid)

}