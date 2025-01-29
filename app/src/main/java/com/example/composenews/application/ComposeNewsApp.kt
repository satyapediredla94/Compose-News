package com.example.composenews.application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composenews.ui.features.navigation.BottomNavigationBar
import com.example.composenews.ui.features.navigation.NavigationalTopBar
import com.example.composenews.ui.features.navigation.NewsDestination
import com.example.composenews.ui.features.navigation.NewsDestination.Home
import com.example.composenews.ui.features.navigation.NewsNavigationGraph

@Composable
fun ComposeNewsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = {
            if (
                backStackEntry?.destination?.id != navController.graph.startDestinationId &&
                backStackEntry?.destination?.route != NewsDestination.Details.route
            ) {
                NavigationalTopBar(navController = navController)
            }
        },
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                route = backStackEntry?.destination?.route ?: Home.route
            )
        }
    ) { innerPadding ->
        NewsNavigationGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}