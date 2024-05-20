package mx.edu.itm.link.pokedex.pokemon.domain.repository.local

import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonEntity
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

interface LocalPokemonRepository {
    suspend fun getPokemons(): Flow<List<Pokemon>>
    suspend fun savePokemon(pokemon:Pokemon)
    suspend fun getPokemonById(id:Int):PokemonEntity
    suspend fun getPokemonsByUserId(userId:String):Flow<List<Pokemon>>
    suspend fun deletePokemon(pokemon:Pokemon)
}