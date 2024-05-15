package mx.edu.itm.link.pokedex.core.util

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Activity.snackBar(msg:String?){
    if (msg.isNullOrBlank()){
        Snackbar.make(View(this),"Error desconocido",Snackbar.LENGTH_LONG).show()
    }else{
        Snackbar.make(View(this),msg,Snackbar.LENGTH_LONG).show()
    }
}

fun Activity.toast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}