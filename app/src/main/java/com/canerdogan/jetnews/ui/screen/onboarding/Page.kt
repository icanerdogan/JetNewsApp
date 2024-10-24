package com.canerdogan.jetnews.ui.screen.onboarding

import androidx.annotation.DrawableRes
import com.canerdogan.jetnews.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = "Stay Updated with Daily News",
        description = "Get the latest headlines and trending stories from around the world, all in one place. Stay informed with real-time updates on news that matters to you.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Search and Discover Easily",
        description = "Instantly find the news you're looking for with our powerful search feature. Whether it's global events or niche topics, JetNews helps you discover stories quickly.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Bookmark and Read Later",
        description = "Save articles you love and access them anytime, anywhere. With JetNews, bookmarking stories for future reading has never been easier.",
        image = R.drawable.onboarding3
    )
)


