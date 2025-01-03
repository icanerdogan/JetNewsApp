package com.canerdogan.jetnews.domain.usecases.news

import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.domain.repository.NewsRepository

class UpsertArticleDatabase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticleRepository(article)
    }

}