package andre.dev.news

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface NewsComponent {
    // Your component methods

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): NewsComponent
    }
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}