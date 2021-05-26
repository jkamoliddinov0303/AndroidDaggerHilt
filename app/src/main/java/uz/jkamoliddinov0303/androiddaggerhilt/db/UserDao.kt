package uz.jkamoliddinov0303.androiddaggerhilt.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User?)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT *FROM user_table")

    fun getAllUsers(): LiveData<List<User>>
}