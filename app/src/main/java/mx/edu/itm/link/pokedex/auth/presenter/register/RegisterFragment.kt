package mx.edu.itm.link.pokedex.auth.presenter.register

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignUp
import mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel.RegisterViewModel
import mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel.RegisterViewModelFactory
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.core.util.toast
import mx.edu.itm.link.pokedex.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authRepo=(requireActivity().application as MyApplication).authRepo
        val firestoreRepo=(requireActivity().application as MyApplication).firestoreRepo

        val registerUseCase= SignUp(authRepo, firestoreRepo)

        val viewmodelFactory= RegisterViewModelFactory(registerUseCase)

        viewModel= ViewModelProvider(this,viewmodelFactory)[RegisterViewModel::class.java]

        observers()

    }

    private fun observers() {
        viewModel.register.observe(requireActivity()){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    toast(msg = "Registrado con exito")
                    findNavController().popBackStack()
                }
                is ResponseStatus.Error->{
                    val view:View=binding.root
                    snackBar(response.error,view)
                }
            }

        }
    }

    fun register(view:View){
        createNewuser()
    }

    private fun createNewuser(){
        val name=binding.tfName.editText?.text.toString()
        val lastname=binding.tfSurnames.editText?.text.toString()
        val email=binding.tfEmail.editText?.text.toString()
        val pass=binding.tfPassword.editText?.text.toString()
        val rnds = (0..10).random()

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastname) &&!TextUtils.isEmpty(email) &&!TextUtils.isEmpty(pass)){
            val cred= Credentials(
                email = email,
                password = pass
            )
            val user= User(
                name = name,
                surNames = lastname,
                level = rnds
            )
            viewModel.register(credentialData = cred, userData = user)
        }

    }



}