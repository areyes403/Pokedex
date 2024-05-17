package mx.edu.itm.link.pokedex.core.presenter

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import mx.edu.itm.link.pokedex.auth.data.remote.AuthRepositoryImp
import mx.edu.itm.link.pokedex.core.data.remote.RetrofitModule
import mx.edu.itm.link.pokedex.core.data.room.DatabaseManager
import mx.edu.itm.link.pokedex.pokemon.data.remote.PokemonApiService
import mx.edu.itm.link.pokedex.pokemon.data.repository.PokemonRepositoryImp

open class MyApplication: Application(){
    lateinit var authRepo:AuthRepositoryImp
    lateinit var pokemonRepo:PokemonRepositoryImp
    override fun onCreate() {
        DatabaseManager.instance.initializeDb(applicationContext)
        authRepo= AuthRepositoryImp(Firebase.auth,Firebase.firestore)
        pokemonRepo= PokemonRepositoryImp(RetrofitModule.apiPokemon)
        super.onCreate()
    }

}