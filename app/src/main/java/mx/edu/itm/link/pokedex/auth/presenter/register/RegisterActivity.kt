package mx.edu.itm.link.pokedex.auth.presenter.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignUp
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModel
import mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel.RegisterViewModel
import mx.edu.itm.link.pokedex.auth.presenter.register.viewmodel.RegisterViewModelFactory
import mx.edu.itm.link.pokedex.core.MyApplication
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.core.util.toast
import mx.edu.itm.link.pokedex.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        val repo=(application as MyApplication).authRepo

        val registerUseCase=SignUp(repo)

        val viewmodelFactory=RegisterViewModelFactory(registerUseCase)

        viewModel= ViewModelProvider(this,viewmodelFactory)[RegisterViewModel::class.java]


        observers()
    }

    private fun observers() {
        viewModel.register.observe(this){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    toast(msg = response.data)
                    this.finish()
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
            val cred=Credentials(
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