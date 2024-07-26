package andre.dev.news

import andre.dev.presentation.ResourceProvider
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

object NewsModuleInitializer {
    private lateinit var newsComponent: NewsComponent

    fun init(
        applicationContext: Context,
        dispatcher: CoroutineDispatcher,
        resourceProvider: ResourceProvider
    ) {
        newsComponent = DaggerNewsComponent.builder()
            .applicationContext(applicationContext)
            .dispatcher(dispatcher)
            .resourceProvider(resourceProvider)
            .build()
    }

    fun getNewsComponent(): NewsComponent {
        if (!::newsComponent.isInitialized) {
            throw IllegalStateException("NewsModuleInitializer must be initialized first")
        }
        return newsComponent
    }
}