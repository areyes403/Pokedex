package mx.edu.itm.link.pokedex.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.edu.itm.link.pokedex.core.util.RoomConstants.DATABASE_VERSION
import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonDao
import mx.edu.itm.link.pokedex.pokemon.data.local.PokemonEntity
import mx.edu.itm.link.pokedex.user.data.local.LocalUserDao
import mx.edu.itm.link.pokedex.user.data.local.LocalUserEntity

@Database(
    entities = [PokemonEntity::class,LocalUserEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){

    abstract fun pokemonDao(): PokemonDao
    abstract fun localUserDao():LocalUserDao
}