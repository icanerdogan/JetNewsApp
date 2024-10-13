package com.canerdogan.jetnews.domain.usecases.news

import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.domain.repository.NewsRepository

class SelectBookmarkArticleDatabase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectBookmarkArticleRepository(url)
    }

}