package mx.edu.itm.link.pokedex.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_POKEMONS)
data class PokemonEntity(
    @PrimaryKey val idPokemon:Int,
    val idUser:String,
    val namepokemon:String,
    val hp:String,
    val attack:String,
    val defense:String,
    val speed:String,
    val weight:String,
)

fun PokemonEntity.toPokemon()= Pokemon(
    idPokemon,idUser,namepokemon,hp,attack,defense,speed,weight
)