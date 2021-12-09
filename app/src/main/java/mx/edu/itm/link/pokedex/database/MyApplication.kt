package mx.edu.itm.link.pokedex.database

import android.app.Application

open class MyApplication: Application(){
    override fun onCreate() {
        DatabaseManager.instance.initializeDb(applicationContext)
        super.onCreate()
    }
}