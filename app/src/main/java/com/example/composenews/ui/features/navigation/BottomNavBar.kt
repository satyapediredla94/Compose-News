package com.example.composenews.ui.features.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composenews.ui.features.navigation.NewsDestination.Favorites
import com.example.composenews.ui.features.navigation.NewsDestination.Global
import com.example.composenews.ui.features.navigation.NewsDestination.Home

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    route: String,
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = route == Home.route,
            onClick = {
                navController.navigate(Home.route)
            },
            icon = {
                BottomNavItem(item = Home, selectedItem = route)
            }
        )
        NavigationBarItem(
            selected = route == Global.route,
            onClick = {
                navController.navigate(Global.route)
            },
            icon = { BottomNavItem(item = Global, selectedItem = route) }
        )
        NavigationBarItem(
            selected = route == Favorites.route,
            onClick = {
                navController.navigate(Favorites.route)
            },
            icon = { BottomNavItem(item = Favorites, selectedItem = route) }
        )
    }
}