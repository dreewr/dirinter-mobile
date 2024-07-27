package andre.dev.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Singleton
@Component(modules = [LoginModule::class])
interface LoginComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        @BindsInstance
        fun dispatcher(dispatcher: CoroutineDispatcher): Builder

        fun build(): LoginComponent
    }
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}