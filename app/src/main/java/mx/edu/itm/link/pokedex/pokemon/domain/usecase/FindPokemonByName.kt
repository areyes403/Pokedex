package mx.edu.itm.link.pokedex.pokemon.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.repository.remote.PokemonRepository

class FindPokemonByName(
    private val repo: PokemonRepository
) {
    suspend operator fun invoke(nombre:String)=repo.searchPokemonByName(name = nombre)

}