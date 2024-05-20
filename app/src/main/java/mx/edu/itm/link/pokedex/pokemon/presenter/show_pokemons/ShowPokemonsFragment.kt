package mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.itm.link.pokedex.databinding.FragmentShowPokemonsBinding
import mx.edu.itm.link.pokedex.pokemon.data.local.LocalPokemonRepositoryImp
import mx.edu.itm.link.pokedex.pokemon.domain.usecase.GetLocalPokemonsByUserId
import mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons.viewmodel.ShowPokemonsViewModel
import mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons.viewmodel.ShowPokemonsViewModelProvider
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser


class ShowPokemonsFragment : Fragment() {

    private lateinit var binding:FragmentShowPokemonsBinding
    private lateinit var viewModel: ShowPokemonsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentShowPokemonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getUserUseCase=GetLocalUser(LocalUserRepositoryImp())

        val getPokemonsUseCase= GetLocalPokemonsByUserId(LocalPokemonRepositoryImp())

        val viewModelFactory=ShowPokemonsViewModelProvider(getUserUseCase,getPokemonsUseCase)

        viewModel=ViewModelProvider(requireActivity(),viewModelFactory)[ShowPokemonsViewModel::class.java]

        observers()

        /*
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

         */
    }

    private fun observers() {
        viewModel.pokemons.onEach {
            if (it.isNotEmpty()){
                val adapter=ShowPokemonsAdapter(data = it)
                binding.rvPokemons.layoutManager=LinearLayoutManager(requireContext())
                binding.rvPokemons.adapter=adapter
            }
        }.launchIn(lifecycleScope)
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