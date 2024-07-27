package andre.dev.news

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

object LoginModuleInitializer {
    private lateinit var loginComponent: LoginComponent

    fun init(
        applicationContext: Context,
        dispatcher: CoroutineDispatcher
    ) {
        loginComponent = DaggerLoginComponent
            .builder()
            .applicationContext(applicationContext)
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