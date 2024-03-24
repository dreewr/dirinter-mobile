package andre.dev.news

import andre.dev.news.cache.NewsCacheSourceImpl
import andre.dev.news.cache.NewsDatabase
import andre.dev.news.data.NewsCacheSource
import andre.dev.news.data.NewsRemoteSource
import andre.dev.news.data.NewsRepositoryImpl
import andre.dev.news.domain.GetArticlesUseCase
import andre.dev.news.domain.NewsRepository
import andre.dev.news.remote.NewsRemoteSourceImpl
import andre.dev.news.remote.NewsService
import andre.dev.news.remote.NewsServiceFactory
import andre.dev.presentation.NewsViewModel
import android.app.Application
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
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class NewsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindRemoteSource(remote: NewsRemoteSourceImpl): NewsRemoteSource

    @Binds
    abstract fun bindCacheSource(repository: NewsCacheSourceImpl): NewsCacheSource

    companion object {
//        @Provides
//        @Singleton
//        fun provideContext(application: Application): Context = application

        @Provides
        fun provideDoSomething(repository: NewsRepositoryImpl) = GetArticlesUseCase { p1, p2 ->
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