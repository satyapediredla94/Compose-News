package com.example.composenews.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composenews.domain.model.Article
import com.example.composenews.view.home.breaking.BreakingNews
import com.example.composenews.view.home.recommended.HomeArticle
import com.example.composenews.view.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: HomeScreenViewModel.UiState,
    onArticleClick: (Article) -> Unit
) {
    if (uiState.articles.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Breaking News",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineSmall,
            )
            BreakingNews(
                modifier = Modifier.fillMaxWidth(),
                articles = uiState.articles.take(10)
            ) {
                onArticleClick(it)
            }
            if (uiState.articles.size > 10) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Recommendation",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        items(uiState.articles.subList(10, uiState.articles.size)) { article ->
                            HomeArticle(article = article) {
                                onArticleClick(it)
                            }
                        }
                    }
                }

            }
        }
    }
}