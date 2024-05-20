package mx.edu.itm.link.pokedex.pokemon.presenter.search_pokemon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.show
import mx.edu.itm.link.pokedex.databinding.FragmentSearchBinding
import mx.edu.itm.link.pokedex.pokemon.data.local.LocalPokemonRepositoryImp
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.SaveLocalPokemon
import mx.edu.itm.link.pokedex.pokemon.presenter.search_pokemon.viewmodel.SearchPokemonViewModel
import mx.edu.itm.link.pokedex.pokemon.presenter.search_pokemon.viewmodel.SearchPokemonViewModelFactory
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser


class SearchPokemonFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private lateinit var viewModel:SearchPokemonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonRepo=(requireActivity().application as MyApplication).pokemonRepo

        val searchUseCase=FindPokemonByName(pokemonRepo)
        val getUser=GetLocalUser(LocalUserRepositoryImp())
        val savePokemon=SaveLocalPokemon(LocalPokemonRepositoryImp())

        val viewmodelFactory=SearchPokemonViewModelFactory(getUser,searchUseCase,savePokemon)

        viewModel=ViewModelProvider(requireActivity(),viewmodelFactory)[SearchPokemonViewModel::class.java]

        observer()

        binding.btnSearch.setOnClickListener {
            viewModel.search(binding.etxtNamePokemon.text.toString())
        }

        binding.btnFavorite.setOnClickListener {
            viewModel.savePokemon()
            findNavController().popBackStack()
        }

        binding.btnbackSearch.setOnClickListener {

        }
    }

    private fun observer() {
        viewModel.pokemon.observe(requireActivity()){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    binding.etxtNamePokemon.text.clear()
                    binding.btnFavorite.show()
                    binding.cardView.show()


                    binding.txtName.text=response.data.namepokemon
                   }
                is ResponseStatus.Error->{
                    Log.e("pokemonRequest",response.error.toString())
                }
            }
        }
    }

}