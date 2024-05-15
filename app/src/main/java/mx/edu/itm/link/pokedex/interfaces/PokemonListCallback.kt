package mx.edu.itm.link.pokedex.interfaces

import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

interface PokemonListCallback {
    fun onClick(pokemon: Pokemon)
    fun delete(pokemon: Pokemon)
}