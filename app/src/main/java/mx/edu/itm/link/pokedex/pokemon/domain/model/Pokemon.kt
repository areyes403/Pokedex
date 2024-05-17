package mx.edu.itm.link.pokedex.pokemon.domain.model

import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonEntity

class Pokemon(
    idPokemon:Int,
    idUser:String,
    namepokemon:String,
    hp:String,
    attack:String,
    defense:String,
    speed:String,
    weight:String){
    val idPokemon=idPokemon
    val idUser=idUser
    val namepokemon=namepokemon
    val hp=hp
    val attack=attack
    val defense=defense
    val speed=speed
    val weight= weight

}

fun Pokemon.toEntity()= PokemonEntity(
    idPokemon,idUser,namepokemon,hp,attack,defense,speed,weight
)

