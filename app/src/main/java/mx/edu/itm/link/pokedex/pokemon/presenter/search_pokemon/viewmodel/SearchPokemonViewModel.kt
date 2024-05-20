package mx.edu.itm.link.pokedex.pokemon.presenter.search_pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.SaveLocalPokemon
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser

class SearchPokemonViewModel(
    private val getLocalUserUseCase:GetLocalUser,
    private val searchPokemonUseCase:FindPokemonByName,
    private val savePokemonUseCase:SaveLocalPokemon
):ViewModel() {
    private lateinit var user:User

    private val _pokemon=MutableLiveData<ResponseStatus<Pokemon>>()
    val pokemon:LiveData<ResponseStatus<Pokemon>> get() = _pokemon

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        user=getLocalUserUseCase()!!
    }

    fun search(name:String) = viewModelScope.launch(Dispatchers.IO){
        _pokemon.postValue(searchPokemonUseCase(nombre = name))
    }

    fun savePokemon(){
        viewModelScope.launch{
            val d=(pokemon.value as ResponseStatus.Success).data
            d.idUser=user.id
            savePokemonUseCase(data = d)
        }
    }
}