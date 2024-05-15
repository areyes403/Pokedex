package mx.edu.itm.link.pokedex.pokemon.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.repository.PokemonRepository

class FindPokemonByName(
    private val repo:PokemonRepository
) {
    operator fun invoke(nombre:String)=repo.searchPokemonByName(name = nombre)

}