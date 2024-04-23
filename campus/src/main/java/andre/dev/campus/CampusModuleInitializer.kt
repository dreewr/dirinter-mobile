package andre.dev.campus

import android.content.Context

object CampusModuleInitializer {
    private lateinit var campusComponent: CampusComponent

    fun init(applicationContext: Context) {
        campusComponent = DaggerCampusComponent.builder().applicationContext(applicationContext).build()
    }

    fun getNewsComponent(): CampusComponent {
        if (!CampusModuleInitializer::campusComponent.isInitialized) {
            throw IllegalStateException("NewsModuleInitializer must be initialized first")
        }
        return campusComponent
    }
}