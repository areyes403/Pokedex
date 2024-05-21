package mx.edu.itm.link.pokedex.user.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.edu.itm.link.pokedex.core.util.RoomConstants.TABLE_LOCAL_USER

@Dao
interface LocalUserDao {

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:LocalUserEntity)

    @Query("SELECT * FROM $TABLE_LOCAL_USER")
    suspend fun getUser():List<LocalUserEntity>

    @Query("DELETE FROM $TABLE_LOCAL_USER")
    suspend fun deleteAll()
}