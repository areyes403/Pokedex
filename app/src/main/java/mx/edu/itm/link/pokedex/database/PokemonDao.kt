package mx.edu.itm.link.pokedex.database

import androidx.room.*

@Dao
interface PokemonDao {

    @Delete
    fun delete(pokemon:PokemonEntity)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun save(pokemon: PokemonEntity)

    @Query("SELECT*FROM $TABLE_POKEMONS")
    fun getPokemonsFromDatabase():List<PokemonEntity>

    @Query("SELECT*FROM $TABLE_POKEMONS WHERE idPokemon= :query")
    fun getPokemonByid(query:String):PokemonEntity


    //Trabajando con los usuarios
    @Query("SELECT*FROM $TABLE_POKEMONS WHERE idUser= :query")
    fun getPokemonsFromUser(query: String):List<PokemonEntity>
/*
    @Query("SELECT COUNT(idUser) FROM $TABLE_POKEMONS")
    fun getNoPokemons(id:String):Int*/


}