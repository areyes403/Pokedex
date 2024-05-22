package mx.edu.itm.link.pokedex.auth.presenter.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import mx.edu.itm.link.pokedex.R
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModel
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModelFactory
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.databinding.FragmentLoginBinding
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser

class LoginFragment:Fragment() {

    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //repositories
        val authRep=(requireActivity().application as MyApplication).authRepo
        val firestoreRep=(requireActivity().application as MyApplication).firestoreRepo
        val localRepo= LocalUserRepositoryImp()

        //usecases
        val signInUseCase= SignIn(authRep,firestoreRep)
        val localUserUseCase= GetLocalUser(localRepo)

        val loginViewModelFactory= LoginViewModelFactory(signInUseCase,localUserUseCase)

        viewModel= ViewModelProvider(this,loginViewModelFactory)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        val link="https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"

        Glide.with(this).load(link).into(binding.imgMain)

        observers()


    }

    private fun observers() {

        viewModel.login.observe(requireActivity()){response->
            when(response){
                is ResponseStatus.Loading->{
                    Log.i("loginStatus","Success")
                }
                is ResponseStatus.Success->{
                    val newGraph: NavGraph = findNavController().navInflater.inflate(R.navigation.nav_home)
                    findNavController().graph=newGraph
                }
                is ResponseStatus.Error->{
                    val view:View=binding.root
                    snackBar(msg = response.error,view)
                }
            }

        }
    }

    private fun loginUser(){
        val user=binding.tfEmail.editText?.text.toString()
        val pass=binding.tfPassword.editText?.text.toString()

        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(pass)){
            viewModel.login(credential =
            Credentials(
                email = user,
                password = pass
            )
            )
        }
    }
}