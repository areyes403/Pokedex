package mx.edu.itm.link.pokedex.pokemon.data.remote.dto

data class StatInfoDto(
    var base_stat:Int=0,
    var effort:Int=0,
    var stat:StatDto?=null
)
