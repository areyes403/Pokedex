package mx.edu.itm.link.pokedex.pokemon.presenter.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.databinding.FragmentPokemonBinding
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName
import mx.edu.itm.link.pokedex.pokemon.presenter.pokemon.viewmodel.PokemonViewModel
import mx.edu.itm.link.pokedex.pokemon.presenter.pokemon.viewmodel.PokemonViewModelFactory


class PokemonFragment : Fragment() {

    private var _binding:FragmentPokemonBinding?=null
    private lateinit var viewModel:PokemonViewModel
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding= FragmentPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id=arguments?.getString("namePokemon").toString()


        val repo=(requireActivity().application as MyApplication).pokemonRepo

        val findPokemonUseCase=FindPokemonByName(repo)

        val viewModelFactory=PokemonViewModelFactory(findPokemonUseCase)

        viewModel=ViewModelProvider(this,viewModelFactory)[PokemonViewModel::class.java]

        viewModel.findPokemon(name = id)

        observers()

    }

    private fun observers() {
        viewModel.pokemon.observe(requireActivity()){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    binding.apply {
                        txtNameFragmentPokemon.text=response.data.name
                        txtDefensaFragmentPokemon.text="${response.data.defense}"
                        txtAttakFragmentPokemon.text="${response.data.attack}"
                        txtHpFragmentPokemon.text="${response.data.hp}"
                        txtPesoFragmentPokemon.text="${response.data.weight}"
                        txtVelocidadFragmentPokemon.text="${response.data.speed}"
                    }
                }
                is ResponseStatus.Error->{

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}