package mx.edu.itm.link.pokedex.core

import android.app.Application
import mx.edu.itm.link.pokedex.core.data.room.DatabaseManager

open class MyApplication: Application(){
    override fun onCreate() {
        DatabaseManager.instance.initializeDb(applicationContext)
        super.onCreate()
    }
}