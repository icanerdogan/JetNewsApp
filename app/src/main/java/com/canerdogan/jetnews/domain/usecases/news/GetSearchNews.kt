package com.canerdogan.jetnews.domain.usecases.news

import androidx.paging.PagingData
import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String,sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNewsFromRepository(searchQuery = searchQuery,sources = sources)
    }

}