package mx.edu.itm.link.pokedex.pokemon.presenter.search_pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.SaveLocalPokemon
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser

class SearchPokemonViewModelFactory(
    private val getLocalUserUseCase: GetLocalUser,
    private val searchPokemonUseCase:FindPokemonByName,
    private val savePokemonUseCase:SaveLocalPokemon
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchPokemonViewModel(getLocalUserUseCase,searchPokemonUseCase,savePokemonUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}