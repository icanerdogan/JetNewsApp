package com.canerdogan.jetnews.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.util.Dimens.MediumPadding1
import com.canerdogan.jetnews.ui.component.common.ArticlesList
import com.canerdogan.jetnews.ui.component.common.EmptyScreen
import com.canerdogan.jetnews.ui.component.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery.value,
            readOnly = false,
            onClearClick = { state.searchQuery.value = ""},
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        val articles = state.articles?.collectAsLazyPagingItems()

        if (state.searchQuery.value.isEmpty()) {
            EmptyScreen(null)
        }

        if (articles != null) {
            ArticlesList(articles = articles, onClick = { navigateToDetails(it) })
        }
    }

}