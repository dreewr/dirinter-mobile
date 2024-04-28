package andre.dev.news.cache

import andre.dev.news.model.CampusSummaryEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CampusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<CampusSummaryEntity>)

    @Query("SELECT * FROM CampusSummaryEntity")
    suspend fun getSummaries(): List<CampusSummaryEntity>
}