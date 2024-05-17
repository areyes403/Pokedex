package mx.edu.itm.link.pokedex.pokemon.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonDao
import mx.edu.itm.link.pokedex.core.util.toPokemon
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.model.toEntity

class MyAppDataSource (private val pokemonDao: PokemonDao){

    suspend fun getPokemons(): LiveData<List<Pokemon>> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(pokemonDao.getPokemonsFromDatabase().map { it.toPokemon() })
    }

    suspend fun getSinglePokemon(query:String): LiveData<Pokemon> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(pokemonDao.getPokemonByid(query).toPokemon())
    }

    suspend fun getPokemonsFromUser(query:String): LiveData<List<Pokemon>> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(pokemonDao.getPokemonsFromUser(query).map { it.toPokemon()  })
    }
/*
    suspend fun getNoPokemons(query: String):Int= withContext(Dispatchers.IO){
        return@withContext MutableLiveData(pokemonDao.getNoPokemons(query)).toInt()
    }*/


    suspend fun delete(pokemon: Pokemon) = withContext(Dispatchers.IO){
        pokemonDao.delete(pokemon.toEntity())
    }

    suspend fun save(pokemon: Pokemon) = withContext(Dispatchers.IO){
        pokemonDao.save(pokemon.toEntity())
    }


}