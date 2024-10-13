package com.canerdogan.jetnews.ui.screen.details

import com.canerdogan.jetnews.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()

}