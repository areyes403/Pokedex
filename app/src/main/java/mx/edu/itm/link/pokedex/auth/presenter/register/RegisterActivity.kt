package mx.edu.itm.link.pokedex.auth.presenter.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import mx.edu.itm.link.pokedex.auth.domain.model.Credentials
import mx.edu.itm.link.pokedex.core.domain.model.User
import mx.edu.itm.link.pokedex.core.domain.model.ResponseStatus
import mx.edu.itm.link.pokedex.core.util.snackBar
import mx.edu.itm.link.pokedex.core.util.toast
import mx.edu.itm.link.pokedex.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progressBar: ProgressBar
    private val viewModel:RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        progressBar= ProgressBar(this)

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
                    snackBar(response.error)
                }
            }

        }
    }

    fun register(view:View){
        createNewuser()
    }

    private fun createNewuser(){
        val name=binding.etxtNombreRegistrandose.text.toString()
        val lastname=binding.etxtApellidosRegistrandose.text.toString()
        val email=binding.etxtCorreoRegistrandose.text.toString()
        val pass=binding.etxtPasswordRegistrandose.text.toString()
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