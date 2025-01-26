package com.example.composenews.view.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    item: NewsDestination,
    selectedItem: String
) {
    Row(modifier = modifier) {
        Icon(item.icon, contentDescription = item.route)
        if (selectedItem == item.route) {
            Text(
                text = item.label,
                modifier = Modifier.padding(start = 4.dp)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}