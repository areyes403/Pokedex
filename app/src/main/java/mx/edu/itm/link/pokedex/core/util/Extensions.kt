package mx.edu.itm.link.pokedex.core.util

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonEntity
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon

fun Activity.snackBar(msg:String?,view: View){
    if (msg.isNullOrBlank()){
        Snackbar.make(view,"Error desconocido",Snackbar.LENGTH_LONG).show()
    }else{
        Snackbar.make(view,msg,Snackbar.LENGTH_LONG).show()
    }
}

fun Fragment.snackBar(msg:String?,view: View?){
    view?.let {
        if (msg.isNullOrBlank()){
            Snackbar.make(it,"Error desconocido",Snackbar.LENGTH_LONG).show()
        }else{
            Snackbar.make(it,msg,Snackbar.LENGTH_LONG).show()
        }
    }
}

fun Fragment.toast(msg:String?){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
}

fun Activity.toast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun View.show(){
    visibility=View.VISIBLE
}

fun View.hide(){
    visibility=View.GONE
}

fun Flow<List<PokemonEntity>>.toPokemonFlow():Flow<List<Pokemon>> = this.map { pokemonEntityList ->
    pokemonEntityList.map {
        it.toPokemon()
    }
}