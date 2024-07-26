package andre.dev.news

import andre.dev.ResourceProviderImpl
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

object LoginModuleInitializer {
    private lateinit var loginComponent: LoginComponent

    fun init(
        applicationContext: Context,
        dispatcher: CoroutineDispatcher,
        resourceProviderImpl: ResourceProviderImpl
    ) {
        loginComponent = DaggerLoginComponent
            .builder()
            .applicationContext(applicationContext)
            .resourceProvider(resourceProviderImpl)
            .dispatcher(dispatcher)
            .build()
    }

    fun getComponent(): LoginComponent {
        if (!::loginComponent.isInitialized) {
            throw IllegalStateException("${javaClass.name} must be initialized first")
        }
        return loginComponent
    }
}