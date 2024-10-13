package com.canerdogan.jetnews.domain.usecases.news

import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectBookmarkAllArticleDatabase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectBookmarkArticlesRepository()
    }

}