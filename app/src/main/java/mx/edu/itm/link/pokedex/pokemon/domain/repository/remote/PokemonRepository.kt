package mx.edu.itm.link.pokedex.pokemon.domain.repository.remote

import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

interface PokemonRepository {
    suspend fun searchPokemonByName(name:String):ResponseStatus<Pokemon>
}