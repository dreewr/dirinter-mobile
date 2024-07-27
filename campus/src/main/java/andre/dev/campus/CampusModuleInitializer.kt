package andre.dev.campus

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

object CampusModuleInitializer {
    private lateinit var campusComponent: CampusComponent

    fun init(
        applicationContext: Context,
        dispatcher: CoroutineDispatcher,
    ) {

        campusComponent = DaggerCampusComponent
            .builder()
            .dispatcher(dispatcher)
            .applicationContext(applicationContext)
            .build()
    }

    fun getNewsComponent(): CampusComponent {
        if (!CampusModuleInitializer::campusComponent.isInitialized) {
            throw IllegalStateException("CampusModuleInitializer must be initialized first")
        }
        return campusComponent
    }
}