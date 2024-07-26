package andre.dev.dirintermobile

import andre.dev.ResourceProviderImpl
import andre.dev.campus.CampusModuleInitializer
import andre.dev.news.LoginModuleInitializer
import andre.dev.news.NewsModuleInitializer
import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeModules(
            NewsModuleInitializer::init,
            LoginModuleInitializer::init,
            CampusModuleInitializer::init
        )
    }

    private fun initializeModules(
        vararg initializers: (
            Context, CoroutineDispatcher, ResourceProviderImpl
        ) -> Unit
    ) {
        initializers.forEach { initializer ->
            initializer(
                this,
                Dispatchers.IO,
                ResourceProviderImpl(this)
            )
        }
    }
}