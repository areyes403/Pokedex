package mx.edu.itm.link.pokedex.pokemon.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.repository.local.LocalPokemonRepository

class GetLocalPokemonById(
    private val repo:LocalPokemonRepository
) {
    suspend operator fun invoke(id:Int) = repo.getPokemonById(id = id)
}