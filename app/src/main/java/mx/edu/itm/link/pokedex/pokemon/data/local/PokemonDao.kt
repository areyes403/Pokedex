package mx.edu.itm.link.pokedex.pokemon.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.pokedex.core.util.RoomConstants.TABLE_POKEMONS

@Dao
interface PokemonDao {
    @Delete
    suspend fun delete(pokemon: PokemonEntity)
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun save(pokemon: PokemonEntity)
    @Query("SELECT*FROM $TABLE_POKEMONS")
    fun getPokemonsFromDatabase(): Flow<List<PokemonEntity>>
    @Query("SELECT*FROM $TABLE_POKEMONS WHERE id= :id")
    suspend fun getPokemonByid(id:Int): PokemonEntity
    @Query("SELECT*FROM $TABLE_POKEMONS WHERE idUser= :userid")
    fun getPokemonsFromUser(userid: String):Flow<List<PokemonEntity>>

    @Query("DELETE FROM $TABLE_POKEMONS")
    suspend fun deleteAll()
}