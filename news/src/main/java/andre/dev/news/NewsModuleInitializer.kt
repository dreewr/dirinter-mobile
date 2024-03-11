package andre.dev.news

import android.content.Context

object NewsModuleInitializer {
    private lateinit var newsComponent: NewsComponent

    fun init(applicationContext: Context) {
        newsComponent = DaggerNewsComponent.builder().applicationContext(applicationContext).build()
    }

    fun getNewsComponent(): NewsComponent {
        if (!::newsComponent.isInitialized) {
            throw IllegalStateException("NewsModuleInitializer must be initialized first")
        }
        return newsComponent
    }
}