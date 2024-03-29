package andre.dev.news.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleSummaryEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao


    companion object {
        fun getInstance(context: Context): NewsDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java, "news.db"
            ).build()

    }
}
