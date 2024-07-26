package andre.dev.campus

import andre.dev.presentation.ResourceProvider
import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

object CampusModuleInitializer {
    private lateinit var campusComponent: CampusComponent

    fun init(
        applicationContext: Context,
        dispatcher: CoroutineDispatcher,
        resourceProvider: ResourceProvider
    ) {

        campusComponent = DaggerCampusComponent
            .builder()
            .dispatcher(dispatcher)
            .applicationContext(applicationContext)
            .resourceProvider(resourceProvider)
            .build()
    }

    fun getNewsComponent(): CampusComponent {
        if (!CampusModuleInitializer::campusComponent.isInitialized) {
            throw IllegalStateException("CampusModuleInitializer must be initialized first")
        }
        return campusComponent
    }
}