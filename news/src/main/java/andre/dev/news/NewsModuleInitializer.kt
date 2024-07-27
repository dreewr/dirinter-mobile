package andre.dev.news

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

object NewsModuleInitializer {
    private lateinit var newsComponent: NewsComponent

    fun init(
        applicationContext: Context,
        dispatcher: CoroutineDispatcher) {
        newsComponent = DaggerNewsComponent.builder()
            .applicationContext(applicationContext)
            .dispatcher(dispatcher)
            .build()
    }

    fun getNewsComponent(): NewsComponent {
        if (!::newsComponent.isInitialized) {
            throw IllegalStateException("NewsModuleInitializer must be initialized first")
        }
        return newsComponent
    }
}