package andre.dev.news.data

interface NewsCacheSource {
    fun insertAll(news: List<String>)

    @Deprecated("not needed")
    fun getAllNews(): List<String>





    @Deprecated("not needed")
    fun getMostRecentTimestamp(): Long?

}