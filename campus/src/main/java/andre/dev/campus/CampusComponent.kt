package andre.dev.campus

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CampusModule::class])
interface CampusComponent {
    // Your component methods

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): CampusComponent
    }
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}