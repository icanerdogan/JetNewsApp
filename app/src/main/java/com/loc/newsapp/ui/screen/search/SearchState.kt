package com.loc.newsapp.ui.screen.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    var searchQuery: MutableState<String> = mutableStateOf(""),
    val articles: Flow<PagingData<Article>>? = null
)