package andre.dev.news.cache

import androidx.room.Dao

@Dao
interface CampusDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(articles: List<ArticleSummaryEntity>)
//
//    @Query("SELECT * FROM ArticleSummaryEntity " +
//            "WHERE timestamp < :startTimestamp " +
//            "ORDER BY timestamp DESC LIMIT :pageSize")
//    suspend fun getArticleSummaries(startTimestamp: Long, pageSize: Int): List<ArticleSummaryEntity>
//
//    @Query("DELETE FROM ArticleSummaryEntity WHERE expirationTimestamp < :currentTime")
//    suspend fun deleteExpiredArticleSummaries(currentTime: Long)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertArticle(article: CampusEntity)
//
//    @Query("SELECT * FROM CampusEntity WHERE id = :id AND expirationTimestamp > :currentTime LIMIT 1")
//    suspend fun getArticleById(id: String, currentTime: Long): CampusEntity?
//
//    @Query("DELETE FROM CampusEntity WHERE expirationTimestamp < :currentTime")
//    suspend fun deleteExpiredArticles(currentTime: Long)

}