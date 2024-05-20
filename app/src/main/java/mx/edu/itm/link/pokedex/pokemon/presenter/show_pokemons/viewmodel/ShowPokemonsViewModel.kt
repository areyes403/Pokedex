package mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.GetLocalPokemonsByUserId
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser

class ShowPokemonsViewModel(
    private val getUserUseCase:GetLocalUser,
    private val getPokemonsUseCase:GetLocalPokemonsByUserId
): ViewModel() {
    private lateinit var user:User
    private val _pokemons=MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons:StateFlow<List<Pokemon>> get() = _pokemons
    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {

            user=getUserUseCase()!!

            getPokemonsUseCase(user.id).collect{
                _pokemons.value=it
            }
        }
    }




}