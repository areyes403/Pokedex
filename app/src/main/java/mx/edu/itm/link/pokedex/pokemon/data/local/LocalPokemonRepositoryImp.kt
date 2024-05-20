package mx.edu.itm.link.pokedex.pokemon.data.local

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.edu.itm.link.pokedex.core.data.room.DatabaseManager
import mx.edu.itm.link.pokedex.core.util.toEntity
import mx.edu.itm.link.pokedex.core.util.toPokemon
import mx.edu.itm.link.pokedex.core.util.toPokemonFlow
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.repository.local.LocalPokemonRepository

class LocalPokemonRepositoryImp: LocalPokemonRepository  {

    private val pokemonDao=DatabaseManager.instance.database.pokemonDao()

    override suspend fun getPokemons(): Flow<List<Pokemon>> = pokemonDao.getPokemonsFromDatabase().toPokemonFlow()

    override suspend fun savePokemon(pokemon: Pokemon) {
        try {
            pokemonDao.save(pokemon = pokemon.toEntity())
        }finally {
            Log.i("pokemonDao","Save completed")
        }
    }

    override suspend fun getPokemonById(id: Int): PokemonEntity = pokemonDao.getPokemonByid(id = id)

    override suspend fun getPokemonsByUserId(userId: String): Flow<List<Pokemon>> = pokemonDao.getPokemonsFromUser(userId).toPokemonFlow()

    override suspend fun deletePokemon(pokemon: Pokemon) {
        try {
            pokemonDao.delete(pokemon = pokemon.toEntity())
        }finally {
            Log.i("pokemonDao","Delete completed")
        }
    }

}