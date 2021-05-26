package uz.jkamoliddinov0303.androiddaggerhilt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User(
    @ColumnInfo(name = "username") var name: String,
    @ColumnInfo(name = "country") var country: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}