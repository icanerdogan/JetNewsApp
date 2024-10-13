package com.canerdogan.jetnews.domain.usecases.news

import androidx.paging.PagingData
import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetAllNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNewsFromRepository(sources = sources)
    }

}