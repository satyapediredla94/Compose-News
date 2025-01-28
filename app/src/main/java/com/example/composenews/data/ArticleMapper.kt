package com.example.composenews.data

import com.example.composenews.data.model.toparticles.Result
import com.example.composenews.domain.model.Article
import com.example.composenews.utils.Mapper
import com.example.composenews.utils.orEmpty

class ArticleMapper : Mapper<Result, Article> {
    override fun map(item: Result): Article = with(item) {
        Article(
            title = webTitle,
            url = id,
            image = if (elements.isNotEmpty()) elements.first().assets.first().file else "",
            publishedAt = webPublicationDate,
            category = sectionName,
            author = if (tags.isNotEmpty()) tags.first().webTitle else "",
            authorImage = if (tags.isNotEmpty()) tags.first().bylineImageUrl.orEmpty() else ""
        )
    }
}