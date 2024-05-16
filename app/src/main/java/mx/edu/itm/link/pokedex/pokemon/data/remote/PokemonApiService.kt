package mx.edu.itm.link.pokedex.pokemon.data.remote

import mx.edu.itm.link.pokedex.pokemon.data.remote.dto.PokemonDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun searchPokemonByName(@Path("name") name:String): Response<PokemonDto>
}