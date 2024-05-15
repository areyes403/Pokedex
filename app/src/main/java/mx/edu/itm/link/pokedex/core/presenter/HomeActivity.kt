package mx.edu.itm.link.pokedex.core.presenter

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.itm.link.pokedex.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        val view=binding.root

        val actionBar: ActionBar? = actionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        //binding.txtTest.text=intent.getStringExtra("email")
        setContentView(view)
    }
}