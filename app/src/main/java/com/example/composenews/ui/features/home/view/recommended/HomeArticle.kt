package com.example.composenews.ui.features.home.view.recommended

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composenews.domain.model.Article
import com.example.composenews.ui.features.common.Author

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeArticle(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(175.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { onClick(article) }
    ) {
        Row(
            modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                model = article.image,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = article.title
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) {
                Text(
                    text = article.category,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = article.title,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 4.dp),
                    fontWeight = FontWeight.SemiBold
                )
                if (article.authorImage.isNotEmpty()) {
                    Author(author = article.author, authorImage = article.authorImage)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = article.publishedAt,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeArticlePreview() {
    HomeArticle(
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