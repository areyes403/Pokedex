package mx.edu.itm.link.pokedex.pokemon.data.remote.dto

data class PokemonDto(
    val id:Int,
    val name:String,
    val order:Int,
    val weight:Int,
    val location_area_encounters:String
    //val hp:String,
    //val attack:String,
    //val defense:String,
    //val speed:String,
    //val weight:String
)
