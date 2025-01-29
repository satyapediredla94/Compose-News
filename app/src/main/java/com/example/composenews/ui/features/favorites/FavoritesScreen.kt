package com.example.composenews.ui.features.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Favorites Screen", textAlign = TextAlign.Center)
    }
}