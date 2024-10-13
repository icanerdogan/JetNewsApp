package com.canerdogan.jetnews.dependencyinjection

import com.canerdogan.jetnews.data.database.NewsDao
import com.canerdogan.jetnews.domain.repository.NewsRepository
import com.canerdogan.jetnews.domain.usecases.news.DeleteArticleDatabase
import com.canerdogan.jetnews.domain.usecases.news.GetAllNews
import com.canerdogan.jetnews.domain.usecases.news.NewsUseCase
import com.canerdogan.jetnews.domain.usecases.news.GetSearchNews
import com.canerdogan.jetnews.domain.usecases.news.SelectBookmarkArticleDatabase
import com.canerdogan.jetnews.domain.usecases.news.SelectBookmarkAllArticleDatabase
import com.canerdogan.jetnews.domain.usecases.news.UpsertArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCase {
        return NewsUseCase(
            getAllNews = GetAllNews(newsRepository),
            getSearchNews = GetSearchNews(newsRepository),
            upsertArticleDatabase = UpsertArticleDatabase(newsRepository),
            deleteArticleDatabase = DeleteArticleDatabase(newsRepository),
            selectBookmarkAllArticleDatabase = SelectBookmarkAllArticleDatabase(newsRepository),
            selectBookmarkArticleDatabase = SelectBookmarkArticleDatabase(newsRepository)
        )
    }

}