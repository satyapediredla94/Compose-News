package com.example.composenews.ui.features.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NewsDestination(val route: String, val icon: ImageVector, val label: String) {
    data object Home : NewsDestination("home", Icons.Default.Home, "Home")

    data object Details : NewsDestination("details", Icons.Filled.AccountBox, "Details")

    data object Global : NewsDestination("global", Icons.Default.Settings, "Global")

    data object Favorites : NewsDestination("favorites", Icons.Default.Favorite, "Favorites")
}