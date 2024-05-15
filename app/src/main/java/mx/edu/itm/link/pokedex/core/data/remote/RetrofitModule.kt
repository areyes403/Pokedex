package mx.edu.itm.link.pokedex.core.data.remote

import mx.edu.itm.link.pokedex.core.util.NetworkConstants
import mx.edu.itm.link.pokedex.pokemon.data.remote.PokemonApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {
    internal object RetrofitClient{
        val retrofit:Retrofit by lazy {
            Retrofit
                .Builder()
                .baseUrl(NetworkConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
    companion object PokemonClient{
        val apiPokemon:PokemonApiService by lazy {
            RetrofitClient.retrofit.create(PokemonApiService::class.java)
        }
    }
}