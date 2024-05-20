package mx.edu.itm.link.pokedex.pokemon.domain.usecase

import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.pokemon.domain.repository.local.LocalPokemonRepository

class DeleteLocalPokemon(
    private val repo:LocalPokemonRepository
) {
    suspend operator fun invoke(data:Pokemon) = repo.deletePokemon(pokemon = data)
}