package com.example.composenews.view.home.breaking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composenews.domain.model.Article

@Composable
fun BreakingNewsArticle(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(200.dp)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = article.image,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .height(125.dp)
                    .fillMaxSize()
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = Color.DarkGray.copy(alpha = 0.5f),
                    )
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = article.category,
                    color = Color.White,
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = article.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                if (article.publishedAt.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = article.publishedAt,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun HomeArticlePreview() {
    BreakingNewsArticle(
        article = Article(
            title = "Reeves thinks big on planning and growth with housebuilding project with a high",
            url = "https://content.guardianapis.com/global/2025/jan/26/reeves-thinks-big-on-planning-and-growth-with-housebuilding-project",
            image = "https://media.guim.co.uk/0ef4d92bf56211f68802b5b36d0082247b498f04/0_1747_11648_6989/1000.jpg",
            "23rd Dec, 2024",
            author = "Satya Pediredla",
            authorImage = "https://uploads.guim.co.uk/2018/02/19/Michael-Savage.jpg",
            category = "Global"
        )
    )
}