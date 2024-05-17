package mx.edu.itm.link.pokedex.user.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.edu.itm.link.pokedex.core.util.RoomConstants.TABLE_LOCAL_USER

@Entity(tableName = TABLE_LOCAL_USER)
data class LocalUserEntity(
    @PrimaryKey
    var id:String="",
    var name:String="",
    var surNames:String="",
    var level:Int=0
)
