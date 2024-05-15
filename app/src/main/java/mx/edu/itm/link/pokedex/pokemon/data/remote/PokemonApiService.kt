package mx.edu.itm.link.pokedex.pokemon.data.remote

import mx.edu.itm.link.pokedex.pokemon.data.remote.dto.PokemonDto
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    fun searchPokemonByName(@Path("id") name:String): Response<PokemonDto>
}