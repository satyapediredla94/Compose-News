package com.example.composenews.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composenews.domain.model.Article
import com.example.composenews.view.details.DetailsScreen
import com.example.composenews.view.details.viewmodel.ArticleDetailsIntent
import com.example.composenews.view.details.viewmodel.ArticleDetailsViewModel
import com.example.composenews.view.favorites.FavoritesScreen
import com.example.composenews.view.global.GlobalScreen
import com.example.composenews.view.home.HomeScreen
import com.example.composenews.view.home.viewmodel.HomeScreenIntent
import com.example.composenews.view.home.viewmodel.HomeScreenViewModel

@Composable
fun NewsNavigationGraph(
    modifier: Modifier = Modifier,
    startDestination: String = NewsDestination.Home.route,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NewsDestination.Home.route) {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(uiState.articles.isEmpty()) {
                viewModel.handleIntent(HomeScreenIntent.GetArticles)
            }
            HomeScreen(
                navController = navController,
                uiState = uiState
            ) {
                navController.currentBackStackEntry?.savedStateHandle?.set("article", it)
                navController.navigate(NewsDestination.Details.route)
            }
        }
        composable(
            route = NewsDestination.Details.route
        ) {
            val article =
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
            val viewModel: ArticleDetailsViewModel = hiltViewModel()
            viewModel.handleIntent(ArticleDetailsIntent.GetArticleDetails(article))
            val uiState by viewModel.uiState.collectAsState()
            DetailsScreen(uiState = uiState) {
                navController.navigateUp()
            }
        }
        composable(route = NewsDestination.Global.route) {
            GlobalScreen(navController = navController)
        }
        composable(route = NewsDestination.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
    }
}