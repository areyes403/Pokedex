package mx.edu.itm.link.pokedex.core.presenter

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import mx.edu.itm.link.pokedex.auth.data.remote.AuthRepositoryImp
import mx.edu.itm.link.pokedex.core.data.remote.RetrofitModule
import mx.edu.itm.link.pokedex.core.data.room.DatabaseManager
import mx.edu.itm.link.pokedex.pokemon.data.repository.PokemonRepositoryImp
import mx.edu.itm.link.pokedex.user.data.repository.UserRepositoryImp

open class MyApplication: Application(){
    lateinit var authRepo:AuthRepositoryImp
    lateinit var pokemonRepo: PokemonRepositoryImp
    lateinit var userRepo:UserRepositoryImp
    override fun onCreate() {
        val auth=Firebase.auth
        val firestore=Firebase.firestore
        DatabaseManager.instance.initializeDb(applicationContext)
        authRepo= AuthRepositoryImp(auth,firestore)
        pokemonRepo= PokemonRepositoryImp(RetrofitModule.apiPokemon)
        userRepo=UserRepositoryImp(firestore)
        super.onCreate()
    }

}