package mx.edu.itm.link.pokedex.core.presenter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.launch
import mx.edu.itm.link.pokedex.R
import mx.edu.itm.link.pokedex.databinding.ActivityMainBinding
import mx.edu.itm.link.pokedex.user.data.local.LocalUserRepositoryImp
import mx.edu.itm.link.pokedex.user.domain.usecase.GetLocalUser

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController=navHostFragment.navController

        val getUser=GetLocalUser(LocalUserRepositoryImp())
        lifecycleScope.launch {
            if (getUser() == null){
                val inflater=navController.navInflater
                val newGraph:NavGraph = inflater.inflate(R.navigation.nav_auth)
                navController.graph=newGraph
            }else{
                val inflater=navController.navInflater
                val newGraph:NavGraph = inflater.inflate(R.navigation.nav_home)
                navController.graph=newGraph
            }
        }

    }
}