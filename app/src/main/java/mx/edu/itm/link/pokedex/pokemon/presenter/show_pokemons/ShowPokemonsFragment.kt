package mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.itm.link.pokedex.databinding.FragmentShowPokemonsBinding


class ShowPokemonsFragment : Fragment() {

    private lateinit var binding:FragmentShowPokemonsBinding
    private val showViewModel: ShowPokemonsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentShowPokemonsBinding.inflate(layoutInflater)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Firebase.auth.currentUser
        user?.let {
            val uid = it.uid
            Log.d("uid","$uid")
            //print(uid.toString())
            binding.rvPokemons.layoutManager= LinearLayoutManager(view.context)
            showViewModel.getPokemonsUser(uid)
            showViewModel.savedPokemons.observe(viewLifecycleOwner,{pokemonlist->
                if(!pokemonlist.isNullOrEmpty()){
                    val adapter= ShowPokemonsAdapter(pokemonlist)
                    binding.rvPokemons.adapter=adapter
                    for (savedpokemon in pokemonlist){
                        Log.d("obtainedpokemons","from pokemon: ${savedpokemon.idPokemon}")
                    }
                }else{
                    binding.txtTitle.visibility=View.VISIBLE
                    Log.d("obtainedpokemons","Vacion xdxd")
                }
            })
        }
    }
/*
    override fun onClick(pokemon: Pokemon) {
        /*
        val numero=showViewModel.getCountPokemons(pokemon.idUser)
        val bundle=Bundle()
        bundle.putString("numero", numero.toString())*/
        val dir=ShowPokemonsFragmentDirections.actionShowPokemonsFragmentToPokemonFragment(pokemon.idPokemon.toString())
        NavHostFragment.findNavController(this).navigate(dir)
    }

    override fun delete(pokemon: Pokemon) {
        try {
            showViewModel.deletePokemon(pokemon)
            binding.rvPokemons.adapter?.notifyDataSetChanged()
            Toast.makeText(activity,"Eliminado", Toast.LENGTH_SHORT)
        }catch (e:ExceptionInInitializerError){
            print(e)
            Toast.makeText(activity,"Error al eliminar",Toast.LENGTH_SHORT)
        }

    }

 */

}