package mx.edu.itm.link.pokedex.pokemon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.edu.itm.link.pokedex.core.util.RoomConstants.TABLE_POKEMONS
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

@Entity(tableName = TABLE_POKEMONS)
data class PokemonEntity(
    @PrimaryKey val id:Int,
    val idUser:String,
    val name:String,
    val hp:String,
    val attack:String,
    val defense:String,
    val speed:String,
    val weight:String,
)
