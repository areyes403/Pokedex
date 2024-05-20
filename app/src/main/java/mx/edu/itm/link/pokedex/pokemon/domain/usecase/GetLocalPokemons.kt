package mx.edu.itm.link.pokedex.pokemon.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.repository.local.LocalPokemonRepository

class GetLocalPokemons (
    private val repo:LocalPokemonRepository
){
    suspend operator fun invoke() = repo.getPokemons()
}