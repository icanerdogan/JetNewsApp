package com.canerdogan.jetnews.ui.screen.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String): SearchEvent()

    object SearchNews : SearchEvent()
}