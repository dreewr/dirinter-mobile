package andre.dev.news.cache

import andre.dev.news.model.UserEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun loginDao(): UserDao

    companion object {
        fun getInstance(context: Context): LoginDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                LoginDatabase::class.java, "login.db"
            ).build()
    }
}
