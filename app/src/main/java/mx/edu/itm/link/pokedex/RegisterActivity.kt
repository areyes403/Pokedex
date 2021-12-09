package mx.edu.itm.link.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import mx.edu.itm.link.pokedex.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        progressBar= ProgressBar(this)
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference=database.reference.child("users")
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
            binding.progresbar.visibility=View.VISIBLE
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
                task->
                if (task.isComplete){
                    val user:FirebaseUser?=auth.currentUser
                    verifyEmail(user)
                    val userDB=dbReference.child(user?.uid!!)
                    userDB.child("name").setValue(name)
                    userDB.child("lastname").setValue(lastname)
                    userDB.child("email").setValue(email)
                    userDB.child("password").setValue(pass)
                    userDB.child("level").setValue(rnds)
                    //userDB.child("pokemons").setValue("none")
                    action()
                    Toast.makeText(this,"Registrado correctamente",Toast.LENGTH_SHORT)

                }
            }
        }

    }
    private fun action(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()?.addOnCompleteListener(this){
            if(it.isComplete){
                Toast.makeText(this,"Email enviado",Toast.LENGTH_SHORT)
            }else{
                Toast.makeText(this,"Error al enviar email ",Toast.LENGTH_SHORT)
            }
        }
    }

}