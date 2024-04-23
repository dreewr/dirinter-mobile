package andre.dev.dirintermobile

import andre.dev.campus.CampusModuleInitializer
import andre.dev.news.LoginModuleInitializer
import andre.dev.news.NewsModuleInitializer
import android.app.Application

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        NewsModuleInitializer.init(this)
        LoginModuleInitializer.init(this)
        CampusModuleInitializer.init(this)
    }
}