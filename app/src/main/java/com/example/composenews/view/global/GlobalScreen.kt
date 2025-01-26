package com.example.composenews.view.global

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@Composable
fun GlobalScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Global Screen", textAlign = TextAlign.Center)
    }
}