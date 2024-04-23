package andre.dev.news.cache

import andre.dev.news.domain.model.User
import andre.dev.news.model.UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM UserEntity LIMIT 1")
    suspend fun getUser(): UserEntity?
}