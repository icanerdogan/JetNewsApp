package com.canerdogan.jetnews.ui.screen.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.canerdogan.jetnews.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    var searchQuery: MutableState<String> = mutableStateOf(""),
    val articles: Flow<PagingData<Article>>? = null
)