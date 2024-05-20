package mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.GetLocalPokemonsByUserId
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser

class ShowPokemonsViewModelProvider(
    private val getUserUseCase: GetLocalUser,
    private val getPokemonsUseCase: GetLocalPokemonsByUserId
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowPokemonsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShowPokemonsViewModel(getUserUseCase,getPokemonsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}