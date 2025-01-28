package com.example.composenews.view.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Author(
    modifier: Modifier = Modifier,
    author: String,
    authorImage: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Row(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .padding(top = 8.dp)
                .height(25.dp)
                .width(25.dp)
                .border(
                    width = 1.dp,
                    color = textColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .clip(RoundedCornerShape(15.dp)),
            model = authorImage,
            contentDescription = ""
        )
        Text(
            text = author,
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = 8.dp, start = 4.dp)
                .align(Alignment.CenterVertically)
        )
    }
}