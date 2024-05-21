package mx.edu.itm.link.pokedex.user.presenter.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.itm.link.pokedex.R
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.databinding.FragmentHomeBinding
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.ObserveUser
import mx.edu.itm.link.pokedex.user.presenter.home.viewmodel.HomeViewModel
import mx.edu.itm.link.pokedex.user.presenter.home.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localRepo=LocalUserRepositoryImp()
        val userRepo=(requireActivity().application as MyApplication).userRepo

        val observeuser=ObserveUser(userRepo)
        val getLocalUserCase=GetLocalUser(localRepo)

        val viewModelFactory=HomeViewModelFactory(getLocalUserCase,observeuser)

        viewModel=ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]

        observers()

        binding.apply {
            btnSearchPokemon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
            btnviewPokemons.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_showPokemonsFragment)
            }
            btnEditProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_editProfileFragment)
            }
            btnLogOut.setOnClickListener {

            }
        }



    }

    private fun observers() {
        viewModel.user.onEach { response->
            when(response){
                is ResponseStatus.Loading->{

                }

                is ResponseStatus.Success->{

                    binding.txtName.text=response.data.name
                    binding.txtLvl.text=response.data.level.toString()
                    binding.circularProgressBar.apply {
                        val float1 = response.data.level.toFloat();
                        setProgressWithAnimation(float1*10,1000)
                        progressBarColorStart = Color.GREEN
                        progressBarColorEnd = Color.BLUE
                        progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
                    }

                }

                is ResponseStatus.Error->{
                    snackBar(response.error,view)
                }
            }
        }.launchIn(lifecycleScope)
    }
}