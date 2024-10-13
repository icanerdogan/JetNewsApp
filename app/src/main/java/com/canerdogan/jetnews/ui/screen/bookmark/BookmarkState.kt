package com.canerdogan.jetnews.ui.screen.bookmark

import com.canerdogan.jetnews.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
