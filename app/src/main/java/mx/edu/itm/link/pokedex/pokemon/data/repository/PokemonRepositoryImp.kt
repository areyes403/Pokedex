package mx.edu.itm.link.pokedex.pokemon.data.repository

import android.util.Log
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.toPokemon
import mx.edu.itm.link.pokedex.pokemon.data.remote.PokemonApiService
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.repository.PokemonRepository
import retrofit2.Response

class PokemonRepositoryImp(
    private val pokeApi:PokemonApiService
):PokemonRepository {

    override suspend fun searchPokemonByName(name: String): ResponseStatus<Pokemon> {
        try {
            val result=pokeApi.searchPokemonByName(name = name)

            if (result.isSuccessful){
                Log.i("retrofitRequest",result.body().toString())
                return ResponseStatus.Success(result.body()!!.toPokemon())
            }

            val error = result.errorBody()
            Log.i("retrofitRequestError",error.toString())
            return ResponseStatus.Error(error.toString())
        }catch (e:Exception){
            Log.e("retrofitRequestError",e.localizedMessage.toString())
            return ResponseStatus.Error(e.localizedMessage)
        }
    }
}