package andre.dev.news.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleSummaryEntity>)

    @Query("SELECT * FROM ArticleSummaryEntity WHERE timestamp < :startTimestamp ORDER BY timestamp DESC LIMIT :pageSize")
    suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<ArticleSummaryEntity>

    @Query("DELETE FROM ArticleSummaryEntity WHERE expirationTimestamp < :currentTime")
    suspend fun deleteExpiredArticles(currentTime: Long)
}