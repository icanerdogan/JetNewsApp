package com.canerdogan.jetnews.ui.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.canerdogan.jetnews.R
import com.canerdogan.jetnews.domain.model.Article
import com.canerdogan.jetnews.ui.component.common.ArticlesList
import com.canerdogan.jetnews.ui.component.common.SearchBar
import com.canerdogan.jetnews.util.Dimens.MediumPadding1
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
    event: (HomeEvent) -> Unit,
) {
    var showBottomDialog by remember { mutableStateOf(false) }
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(80.dp)
                        .padding(start = MediumPadding1)
                )

                IconButton(
                    onClick = {
                        showBottomDialog = true
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_contact),
                        modifier = Modifier.size(30.dp).background(Color.Transparent),
                        tint = Color.DarkGray,
                        contentDescription = "Contact Icon"
                    )
                }

            }

            Spacer(modifier = Modifier.height(5.dp))

            SearchBar(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                text = "",
                readOnly = true,
                onValueChange = {},
                onClick = {
                    navigateToSearch()
                },
                onSearch = {}
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

            val scrollState = rememberScrollState()

            LaunchedEffect(key1 = state.maxScrollingValue) {
                delay(500)
                if (state.maxScrollingValue > 0) {
                    scrollState.animateScrollTo(
                        value = state.maxScrollingValue,
                        animationSpec = infiniteRepeatable(
                            tween(
                                durationMillis = (state.maxScrollingValue - state.scrollValue) * 50_000 / state.maxScrollingValue,
                                easing = LinearEasing,
                                delayMillis = 1000
                            )
                        )
                    )
                }
            }
            // Update the maxScrollingValue
            LaunchedEffect(key1 = scrollState.maxValue) {
                event(HomeEvent.UpdateMaxScrollingValue(scrollState.maxValue))
            }
            // Save the state of the scrolling position
            LaunchedEffect(key1 = scrollState.value) {
                event(HomeEvent.UpdateScrollValue(scrollState.value))
            }

            Text(
                text = titles,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MediumPadding1)
                    .horizontalScroll(scrollState, false),
                fontSize = 12.sp,
                color = colorResource(id = R.color.placeholder)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ArticlesList(
                modifier = Modifier.padding(horizontal = 10.dp),
                articles = articles,
                onClick = {
                    navigateToDetails(it)
                }
            )

            if (showBottomDialog) {
                val sheetState = rememberModalBottomSheetState()
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomDialog = false
                    },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Give Feedback & Contact Us!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Default.MailOutline,
                                contentDescription = "Email Contact Icon"
                            )
                            Text(
                                text = "canerdogan.store@gmail.com",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.DarkGray
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone Contact Icon"
                            )
                            Text(
                                text = "+1-234-567-890",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}

