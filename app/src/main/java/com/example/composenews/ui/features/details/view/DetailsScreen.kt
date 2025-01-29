package com.example.composenews.ui.features.details.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.composenews.domain.model.ArticleDetail
import com.example.composenews.ui.features.common.Author
import com.example.composenews.ui.features.details.viewmodel.ArticleDetailsViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    uiState: ArticleDetailsViewModel.UiState,
    navigateBack: () -> Unit = {}
) {
    uiState.article?.let { article ->
        Box(modifier = modifier.verticalScroll(state = ScrollState(0), enabled = true)) {
            Box(
                modifier = Modifier
                    .height(450.dp)
            ) {
                AsyncImage(
                    model = article.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                val context = LocalContext.current
                var dominantColor by remember { mutableStateOf(Color.Gray) }
                var inverseColor by remember { mutableStateOf(Color.Black) }

                // Load the image and calculate the dominant color using Coil and Palette
                LaunchedEffect(article.image) {
                    val request = ImageRequest.Builder(context)
                        .data(article.image)
                        .allowHardware(false) // Disable hardware to work with Palette
                        .build()

                    val result = (context.imageLoader.execute(request) as? SuccessResult)?.drawable
                    result?.toBitmap()?.let { bitmap ->
                        Palette.from(bitmap).generate { palette ->
                            palette?.dominantSwatch?.rgb?.let { color ->
                                dominantColor = Color(color)
                                inverseColor =
                                    if (Color(color).luminance() > 0.5f) Color.Black else Color.White
                            }
                        }
                    }
                }
                IconButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart),
                    onClick = { navigateBack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = inverseColor
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart)
                        .padding(bottom = 50.dp)
                        .background(
                            color = Color.DarkGray.copy(alpha = 0.5f),
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = article.category,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Text(
                        text = article.title,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    if (article.authorImage.isNotEmpty()) {
                        Author(
                            author = article.author,
                            authorImage = article.authorImage,
                            textColor = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text(
                        text = article.publishedAt,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 400.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = AnnotatedString.fromHtml(article.content),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = TextUnit(1.5f, TextUnitType.Em)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        uiState = ArticleDetailsViewModel.UiState(
            article = ArticleDetail(
                title = "Reeves thinks big on planning and growth with housebuilding project with a high",
                url = "https://content.guardianapis.com/global/2025/jan/26/reeves-thinks-big-on-planning-and-growth-with-housebuilding-project",
                image = "https://media.guim.co.uk/0ef4d92bf56211f68802b5b36d0082247b498f04/0_1747_11648_6989/1000.jpg",
                publishedAt = "23rd Dec, 2024",
                author = "Satya Pediredla",
                authorImage = "https://uploads.guim.co.uk/2018/02/19/Michael-Savage.jpg",
                category = "Global",
                content = "Some of the best content to display is here"
            )
        )
    )
}