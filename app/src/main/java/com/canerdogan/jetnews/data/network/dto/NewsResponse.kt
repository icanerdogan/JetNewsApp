package com.canerdogan.jetnews.data.network.dto

import com.canerdogan.jetnews.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)