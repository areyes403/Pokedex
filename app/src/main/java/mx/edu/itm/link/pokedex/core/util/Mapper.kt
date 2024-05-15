package mx.edu.itm.link.pokedex.core.util

import mx.edu.itm.link.pokedex.pokemon.data.remote.dto.PokemonDto
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

fun PokemonDto.toPokemon():Pokemon = Pokemon(
    idPokemon = id,
    namepokemon = name,
    idUser = "",
    hp = "",
    attack = "",
    defense = "",
    speed = "",
    weight = weight.toString()
)