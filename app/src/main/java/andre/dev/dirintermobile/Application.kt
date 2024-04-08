package andre.dev.dirintermobile

import andre.dev.news.NewsModuleInitializer
import android.app.Application

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        NewsModuleInitializer.init(this)
    }
}