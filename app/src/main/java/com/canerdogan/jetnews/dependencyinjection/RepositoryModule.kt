package com.canerdogan.jetnews.dependencyinjection

import com.canerdogan.jetnews.data.database.NewsDao
import com.canerdogan.jetnews.data.network.NewsAPI
import com.canerdogan.jetnews.data.repository.NewsRepositoryImpl
import com.canerdogan.jetnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsAPI,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)
}