package mx.edu.itm.link.pokedex.pokemon.presenter.pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName
import mx.edu.itm.link.pokedex.user.presenter.home.viewmodel.HomeViewModel

class PokemonViewModelFactory(
    private val findPokemonByNameUseCase: FindPokemonByName
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(findPokemonByNameUseCase = findPokemonByNameUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}