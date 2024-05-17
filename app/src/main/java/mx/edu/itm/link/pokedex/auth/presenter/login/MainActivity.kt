package mx.edu.itm.link.pokedex.auth.presenter.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModel
import mx.edu.itm.link.pokedex.auth.presenter.login.viewmodel.LoginViewModelFactory
import mx.edu.itm.link.pokedex.user.presenter.home.HomeActivity
import mx.edu.itm.link.pokedex.auth.presenter.register.RegisterActivity
import mx.edu.itm.link.pokedex.core.presenter.MyApplication
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.databinding.ActivityMainBinding
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser
import mx.edu.itm.link.pokedex.user.domain.usecase.SaveLocalUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository=(application as MyApplication).authRepo

        val signInUseCase=SignIn(repository)

        val localRepo=LocalUserRepositoryImp()

        val localUserUseCase=GetLocalUser(localRepo)

        val saveUserUseCase=SaveLocalUser(localRepo)

        val loginViewModelFactory=LoginViewModelFactory(signInUseCase,localUserUseCase,saveUserUseCase)

        viewModel= ViewModelProvider(this,loginViewModelFactory)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        val link="https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"

        Glide.with(this).load(link).into(binding.imgMain)

        observers()

    }

    private fun observers() {
        viewModel.user.observe(this){
            if (it!=null){
                val intent=Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.login.observe(this){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    viewModel.saveUser(user = response.data)
                    val intent=Intent(this, HomeActivity::class.java)
                    startActivity(intent)
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
            viewModel.login(data =
                Credentials(
                    email = user,
                    password = pass
                )
            )
        }
    }

}