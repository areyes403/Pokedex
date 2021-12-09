package mx.edu.itm.link.pokedex.interfaces

import android.view.View
import mx.edu.itm.link.pokedex.database.Pokemon

interface PokemonListCallback {
    fun onClick(pokemon:Pokemon)
    fun delete(pokemon: Pokemon)
}