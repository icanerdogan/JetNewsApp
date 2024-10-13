package com.canerdogan.jetnews.ui.screen.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    var sideEffect by mutableStateOf<Boolean?>(null)

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCase.selectBookmarkArticleDatabase(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCase.deleteArticleDatabase(article = article)
        sideEffect = false
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCase.upsertArticleDatabase(article = article)
        sideEffect = true
    }

    fun controlBookmarkedArticle(article: Article) {
        viewModelScope.launch {
            newsUseCase.selectBookmarkAllArticleDatabase().collect {
                sideEffect = it.contains(article)
            }
        }
    }
}