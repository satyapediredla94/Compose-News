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
import com.example.composenews.view.favorites.FavoritesScreen
import com.example.composenews.view.global.GlobalScreen
import com.example.composenews.view.home.HomeScreen
import com.example.composenews.viewmodel.HomeScreenIntent
import com.example.composenews.viewmodel.HomeScreenViewModel

@Composable
fun NewsNavigationGraph(
    modifier: Modifier = Modifier,
    startDestination: String = NewsDestination.Home.route,
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(uiState.articles.isEmpty()) {
        viewModel.handleIntent(HomeScreenIntent.GetArticles)
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NewsDestination.Home.route) {
            HomeScreen(
                navController = navController,
                uiState = uiState
            )
        }
        composable(route = NewsDestination.Global.route) {
            GlobalScreen(navController = navController)
        }
        composable(route = NewsDestination.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
    }
}