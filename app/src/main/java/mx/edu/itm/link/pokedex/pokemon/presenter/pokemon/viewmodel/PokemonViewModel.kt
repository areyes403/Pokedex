package mx.edu.itm.link.pokedex.pokemon.presenter.pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName

class PokemonViewModel(
    private val findPokemonByNameUseCase: FindPokemonByName
):ViewModel() {

    private val _pokemon=MutableLiveData<ResponseStatus<Pokemon>>()
    val pokemon:LiveData<ResponseStatus<Pokemon>> get() = _pokemon

    fun findPokemon(name:String)=viewModelScope.launch(Dispatchers.IO){
        _pokemon.postValue(ResponseStatus.Loading)
        _pokemon.postValue(findPokemonByNameUseCase(name)!!)
    }
}