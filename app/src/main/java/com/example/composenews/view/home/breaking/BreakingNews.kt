package com.example.composenews.view.home.breaking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composenews.domain.model.Article
import com.example.composenews.view.home.HorizontalPagerIndicator

@Composable
fun BreakingNews(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit = {}
) {
    if (articles.isNotEmpty()) {
        Column {
            val pagerState = rememberPagerState(
                initialPage = articles.size / 2,
                pageCount = { articles.size }
            )
            HorizontalPager(
                modifier = modifier.fillMaxWidth(),
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp), // Add padding at the edges
            ) {
                BreakingNewsArticle(article = articles[it], onClick = onClick)
            }

            // Page indicators
            HorizontalPagerIndicator(
                modifier = Modifier.fillMaxWidth(),
                pagerState = pagerState
            )
        }
    }
}