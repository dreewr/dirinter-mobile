package andre.dev.news

import andre.dev.presentation.ResourceProvider
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface NewsComponent {
    // Your component methods

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        @BindsInstance
        fun dispatcher(dispatcher: CoroutineDispatcher): Builder
        @BindsInstance
        fun resourceProvider(resourcesProvider: ResourceProvider): Builder
        fun build(): NewsComponent
    }
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}