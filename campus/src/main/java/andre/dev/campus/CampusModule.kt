package andre.dev.campus

import andre.dev.campus.interactor.GetCampiUseCase
import andre.dev.campus.interactor.GetCampusUseCase
import andre.dev.campus.remote.CampusRemoteSourceImpl
import andre.dev.campus.remote.CampusService
import andre.dev.campus.remote.CampusServiceFactory
import andre.dev.lib.ViewModelFactory
import andre.dev.lib.ViewModelKey
import andre.dev.news.cache.CampusCacheSourceImpl
import andre.dev.news.cache.CampusDatabase
import andre.dev.news.data.CampusCacheSource
import andre.dev.news.data.CampusRemoteSource
import andre.dev.news.data.CampusRepositoryImpl
import andre.dev.presentation.CampusDetailsViewModel
import andre.dev.presentation.CampusViewModel
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
abstract class CampusModule {

    @Binds
    @IntoMap
    @ViewModelKey(CampusViewModel::class)
    abstract fun bindCampusViewModel(viewModel: CampusViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CampusDetailsViewModel::class)
    abstract fun bindCampusDetailsViewModel(viewModel: CampusDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindRepository(repository: CampusRepositoryImpl): CampusRepository

    @Binds
    abstract fun bindRemoteSource(remote: CampusRemoteSourceImpl): CampusRemoteSource

    @Binds
    abstract fun bindCacheSource(repository: CampusCacheSourceImpl): CampusCacheSource

    companion object {

        @Provides
        fun provideGetCampiUseCase(repository: CampusRepository) =
            GetCampiUseCase { repository.getCampi() }

        @Provides
        fun provideGetCampusUseCase(repository: CampusRepository) =
            GetCampusUseCase { p1 -> repository.getCampus(p1) }

        @Provides
        fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Singleton
        @Provides
        fun providesService(): CampusService = CampusServiceFactory().getServiceFactory()

        @Provides
        @Singleton
        fun provideAppDatabase(application: Context) = CampusDatabase.getInstance(application)
    }
}