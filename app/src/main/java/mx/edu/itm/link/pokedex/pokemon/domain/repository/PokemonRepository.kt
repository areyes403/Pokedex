package mx.edu.itm.link.pokedex.pokemon.domain.repository

import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

interface PokemonRepository {
    fun searchPokemonByName(name:String):ResponseStatus<Pokemon>
}