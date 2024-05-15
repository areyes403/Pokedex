package mx.edu.itm.link.pokedex.auth.presenter.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import mx.edu.itm.link.pokedex.auth.data.remote.AuthRepositoryImp
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.auth.domain.usecase.SignIn
import mx.edu.itm.link.pokedex.core.presenter.HomeActivity
import mx.edu.itm.link.pokedex.auth.presenter.register.RegisterActivity
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:LoginViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth= Firebase.auth
        val firestore=Firebase.firestore
        val repository=AuthRepositoryImp(auth,firestore)
        val signInUseCase=SignIn(repository)
        viewModel=ViewModelProvider(this).get(LoginViewModel::class.java)

        observers()
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        val link="https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"

        Glide.with(this).load(link).into(binding.imgMain)
    }

    private fun observers() {
        viewModel.login.observe(this){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    val intent=Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                is ResponseStatus.Error->{
                    snackBar(msg = response.error)
                }
            }

        }
    }

    private fun loginUser(){
        val user=binding.etxtLoggingUser.text.toString()
        val pass=binding.etxtLoggingPass.text.toString()

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