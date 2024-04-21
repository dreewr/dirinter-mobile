package andre.dev.news

import android.content.Context

object LoginModuleInitializer {
    private lateinit var loginComponent: LoginComponent

    fun init(applicationContext: Context) {
        loginComponent = DaggerLoginComponent.builder().applicationContext(applicationContext).build()
    }

    fun getComponent(): LoginComponent {
        if (!::loginComponent.isInitialized) {
            throw IllegalStateException("${javaClass.name} must be initialized first")
        }
        return loginComponent
    }
}