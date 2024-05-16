package mx.edu.itm.link.pokedex.pokemon.presenter.search_pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName

class SearchPokemonViewModelFactory(
    private val findPokemonUseCase:FindPokemonByName
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchPokemonViewModel(findPokemonUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}