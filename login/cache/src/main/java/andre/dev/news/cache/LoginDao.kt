package andre.dev.news.cache

import androidx.room.Dao

@Dao
interface LoginDao {


    //Função
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
//    suspend fun insertArticle(article: ArticleEntity)
//
//    @Query("SELECT * FROM ArticleEntity WHERE id = :id AND expirationTimestamp > :currentTime LIMIT 1")
//    suspend fun getArticleById(id: String, currentTime: Long): ArticleEntity?
//
//    @Query("DELETE FROM ArticleEntity WHERE expirationTimestamp < :currentTime")
//    suspend fun deleteExpiredArticles(currentTime: Long)

}