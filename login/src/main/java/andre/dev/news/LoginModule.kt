package andre.dev.news

import andre.dev.lib.ViewModelFactory
import andre.dev.lib.ViewModelKey
import andre.dev.news.cache.LoginCacheSourceImpl
import andre.dev.news.cache.LoginDatabase
import andre.dev.news.data.LoginCacheSource
import andre.dev.news.data.LoginRemoteSource
import andre.dev.news.data.LoginRepositoryImpl
import andre.dev.news.domain.interactor.ExecuteLoginUseCase
import andre.dev.news.domain.LoginRepository
import andre.dev.news.domain.interactor.GetLoggedUserUseCase
import andre.dev.news.remote.LoginRemoteSourceImpl
import andre.dev.news.remote.LoginService
import andre.dev.news.remote.LoginServiceFactory
import andre.dev.presentation.LoginViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindRemoteSource(remote: LoginRemoteSourceImpl): LoginRemoteSource

    @Binds
    abstract fun bindCacheSource(repository: LoginCacheSourceImpl): LoginCacheSource

    companion object {

        @Provides
        fun provideExecuteLoginUseCase(repository: LoginRepository) = ExecuteLoginUseCase { p1, p2 ->
            repository.executeLogin(p1, p2)
        }

        @Provides
        fun provideGetUserUseCase(repository: LoginRepository) = GetLoggedUserUseCase {
            repository.getLoggedUser()
        }

        @Singleton
        @Provides
        fun providesService(): LoginService = LoginServiceFactory().getServiceFactory()

        @Provides
        @Singleton
        fun provideAppDatabase(application: Context) = LoginDatabase.getInstance(application)
    }
}