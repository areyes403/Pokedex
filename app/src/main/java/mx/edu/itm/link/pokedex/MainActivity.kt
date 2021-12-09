package mx.edu.itm.link.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import mx.edu.itm.link.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth= FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            login(view)
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        val link="https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png"

        Glide.with(this).load(link).into(binding.imgMain)
    }

     fun login(view:View){
         loginUser()
    }

    private fun loginUser(){
        val user=binding.etxtLoggingUser.text.toString()
        val pass=binding.etxtLoggingPass.text.toString()

        if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(pass)){
            auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener (this){
                task->
                if(task.isSuccessful){
                    //action()
                        val intent=Intent(this,HomeActivity::class.java).apply {
                            putExtra("email",user)
                            putExtra("password",pass)
                        }

                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT)
                }
            }
        }else{
            Toast.makeText(this,"Complete todos los campos porfavor",Toast.LENGTH_SHORT)
        }
    }

    private fun action(){
        startActivity(Intent(this,HomeActivity::class.java))
    }
}