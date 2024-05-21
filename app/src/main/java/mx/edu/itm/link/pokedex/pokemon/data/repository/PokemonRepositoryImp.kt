package mx.edu.itm.link.pokedex.pokemon.data.repository

import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.toPokemon
import mx.edu.itm.link.pokedex.pokemon.data.remote.PokemonApiService
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.repository.remote.PokemonRepository

class PokemonRepositoryImp(
    private val pokeApi: PokemonApiService
): PokemonRepository {

    override suspend fun searchPokemonByName(name: String): ResponseStatus<Pokemon> {
        try {
            val result=pokeApi.searchPokemonByName(name = name)

            if (result.isSuccessful){
                return ResponseStatus.Success(result.body()!!.toPokemon())
            }

            val error = result.errorBody()
            return ResponseStatus.Error(error.toString())
        }catch (e:Exception){
            return ResponseStatus.Error(e.localizedMessage)
        }
    }
}