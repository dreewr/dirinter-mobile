package andre.dev.news

import andre.dev.lib.ViewModelKey
import andre.dev.news.cache.NewsCacheSourceImpl
import andre.dev.news.cache.NewsDatabase
import andre.dev.news.data.NewsCacheSource
import andre.dev.news.data.NewsRemoteSource
import andre.dev.news.data.NewsRepositoryImpl
import andre.dev.news.domain.GetArticleByIdUseCase
import andre.dev.news.domain.GetArticlesUseCase
import andre.dev.news.domain.NewsRepository
import andre.dev.news.remote.NewsRemoteSourceImpl
import andre.dev.news.remote.NewsService
import andre.dev.news.remote.NewsServiceFactory
import andre.dev.presentation.NewsDetailsViewModel
import andre.dev.presentation.NewsViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import andre.dev.lib.ViewModelFactory



@Module
abstract class NewsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsViewModel::class)
    abstract fun bindNewsDetailsViewModel(viewModel: NewsDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindRemoteSource(remote: NewsRemoteSourceImpl): NewsRemoteSource

    @Binds
    abstract fun bindCacheSource(repository: NewsCacheSourceImpl): NewsCacheSource

    companion object {

        @Provides
        fun provideGetArticleByIdUseCase(repository: NewsRepositoryImpl) = GetArticleByIdUseCase { p1 ->
            repository.getArticle(p1)
        }

        @Provides
        fun provideGetArticlesUseCase(repository: NewsRepositoryImpl) = GetArticlesUseCase { p1, p2 ->
            repository.getArticles(p1, p2)
        }

        @Provides
        fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Singleton
        @Provides
        fun providesService(): NewsService = NewsServiceFactory().getServiceFactory()

        @Provides
        @Singleton
        fun provideAppDatabase(application: Context) = NewsDatabase.getInstance(application)
    }
}