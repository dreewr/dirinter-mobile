package andre.dev.news.cache

import andre.dev.news.data.NewsCacheSource
import javax.inject.Inject

class NewsCacheSourceImpl @Inject constructor(): NewsCacheSource {
    override fun insertAll(news: List<String>) {
        TODO("Not yet implemented")
    }

    override fun getAllNews(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getMostRecentTimestamp(): Long? {
        TODO("Not yet implemented")
    }

}