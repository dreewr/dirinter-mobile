package andre.dev.news.cache

import andre.dev.news.model.CampusEntity
import andre.dev.news.model.CampusSummaryEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CampusSummaryEntity::class, CampusEntity::class], version = 1, exportSchema = false)
abstract class CampusDatabase : RoomDatabase() {
    abstract fun newsDao(): CampusDao

    companion object {
        fun getInstance(context: Context): CampusDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                CampusDatabase::class.java, "campus.db"
            ).build()

    }
}
