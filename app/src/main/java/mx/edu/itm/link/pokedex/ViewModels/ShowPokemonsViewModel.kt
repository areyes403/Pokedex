package mx.edu.itm.link.pokedex.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.database.DatabaseManager
import mx.edu.itm.link.pokedex.database.MyAppDataSource
import mx.edu.itm.link.pokedex.database.Pokemon

class ShowPokemonsViewModel: ViewModel() {
    val savedPokemons=MutableLiveData<List<Pokemon>>()
    var no=0
    fun getPokemons(){
        viewModelScope.launch {
            val pokemonDao=DatabaseManager.instance.database.pokemonDao()
            savedPokemons.value=MyAppDataSource(pokemonDao).getPokemons().value
        }
    }

    fun getPokemonsUser(query:String){
        viewModelScope.launch {
            val pokemonDao=DatabaseManager.instance.database.pokemonDao()
            savedPokemons.value=MyAppDataSource(pokemonDao).getPokemonsFromUser(query).value
        }
    }
/*
    fun getCountPokemons(query:String){
        viewModelScope.launch {
            val pokemonDao=DatabaseManager.instance.database.pokemonDao()
            no=MyAppDataSource(pokemonDao).getNoPokemons(query)
        }
    }*/

    fun deletePokemon(poke: Pokemon){
        viewModelScope.launch {
            val pokemonDao=DatabaseManager.instance.database.pokemonDao()
            MyAppDataSource(pokemonDao).delete(poke)
        }
    }



}