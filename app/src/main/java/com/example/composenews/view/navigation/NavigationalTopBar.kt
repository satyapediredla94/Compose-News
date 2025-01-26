package com.example.composenews.view.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationalTopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    icon: ImageVector = Icons.Default.KeyboardArrowLeft,
    contentDescription: String = "Back",
    onNavIconClick: () -> Unit = { navController.navigateUp() }
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(onClick = { onNavIconClick() }) {
            Icon(imageVector = icon, contentDescription = contentDescription)
        }
    }
}