package mx.edu.itm.link.pokedex.pokemon.presenter.pokemon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.databinding.FragmentPokemonBinding
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.FindPokemonByName
import mx.edu.itm.link.pokedex.pokemon.presenter.pokemon.viewmodel.PokemonViewModel
import mx.edu.itm.link.pokedex.pokemon.presenter.pokemon.viewmodel.PokemonViewModelFactory
import org.json.JSONObject


class PokemonFragment : Fragment() {

    //private lateinit var queue: RequestQueue
    private var _binding:FragmentPokemonBinding?=null
    private lateinit var viewModel:PokemonViewModel
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding= FragmentPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //queue= Volley.newRequestQueue(activity)
        val id=arguments?.getString("idFragmentPokemon").toString()

        val repo=(requireActivity().application as MyApplication).pokemonRepo

        val findPokemonUseCase=FindPokemonByName(repo)

        val viewModelFactory=PokemonViewModelFactory(findPokemonUseCase)
        viewModel=ViewModelProvider(this,viewModelFactory)[PokemonViewModel::class.java]

        viewModel.findPokemon(name = id)

        observers()

        /*
        print("----------------$id")

        val urli="https://pbs.twimg.com/profile_images/1178942318981701634/d5qM22Ft_400x400.jpg"
        context?.let { Glide.with(it).load(urli).into(binding.imgPokemon) }
        var url="https://pokeapi.co/api/v2/pokemon/${id}"
        val jsonReques= JsonObjectRequest(url, Response.Listener <JSONObject>{
            Log.d("response","$it")
            binding.txtNameFragmentPokemon.text=it.getString("name")
            binding.txtHpFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(0).getString("base_stat")
            binding.txtAttakFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(1).getString("base_stat")
            binding.txtDefensaFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(2).getString("base_stat")
            binding.txtVelocidadFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(5).getString("base_stat")
            binding.txtPesoFragmentPokemon.text=it.getString("weight")
           },
            Response.ErrorListener { Log.d("response","Error") }
        )
        queue.add(jsonReques)



         */
    }

    private fun observers() {
        viewModel.pokemon.observe(requireActivity()){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    binding.apply {
                        txtNameFragmentPokemon.text=response.data.namepokemon
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