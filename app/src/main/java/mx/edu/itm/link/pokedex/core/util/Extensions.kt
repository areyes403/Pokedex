package mx.edu.itm.link.pokedex.core.util

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

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

fun Activity.toast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}

fun View.show(){
    visibility=View.VISIBLE
}

fun View.hide(){
    visibility=View.GONE
}