package mx.edu.itm.link.pokedex.core.util

import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonEntity
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.pokemon.data.remote.dto.PokemonDto
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.user.data.local.LocalUserEntity

fun PokemonDto.toPokemon():Pokemon = Pokemon(
    idPokemon = 0,
    namepokemon = name,
    idUser = "",
    hp = "",
    attack = "",
    defense = "",
    speed = "",
    weight = weight.toString()
)

fun PokemonEntity.toPokemon()= Pokemon(
    id,idUser,name,hp,attack,defense,speed,weight
)

fun LocalUserEntity.toUser() = User(
    name = name,
    id = id,
    surNames = surNames,
    level = level
)

fun User.toLocalUserEntity() = LocalUserEntity(
    id = id,
    name = name,
    surNames = surNames,
    level = level
)