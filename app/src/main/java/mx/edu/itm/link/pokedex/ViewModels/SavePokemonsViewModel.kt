package mx.edu.itm.link.pokedex.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.database.DatabaseManager
import mx.edu.itm.link.pokedex.database.MyAppDataSource
import mx.edu.itm.link.pokedex.database.Pokemon

class SavePokemonsViewModel: ViewModel(){

    fun save(pokemon: Pokemon){
        viewModelScope.launch {
            val pokemonDao=DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).save(pokemon)
        }
    }
}