package andre.dev.news

import andre.dev.news.data.NewsRepositoryImpl
import andre.dev.news.domain.DoSomething
import andre.dev.ui.NewsDependencyProvider
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
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