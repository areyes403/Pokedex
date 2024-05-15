package mx.edu.itm.link.pokedex.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.core.data.room.DatabaseManager
import mx.edu.itm.link.pokedex.pokemon.data.repository.MyAppDataSource
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

class SavePokemonsViewModel: ViewModel(){

    fun save(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao= DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).save(pokemon)
        }
    }
}