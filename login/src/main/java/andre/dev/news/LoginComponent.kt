package andre.dev.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LoginModule::class])
interface LoginComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): LoginComponent
    }
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}