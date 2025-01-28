package com.example.composenews.data

import com.example.composenews.data.model.articledetails.Content
import com.example.composenews.domain.model.ArticleDetail
import com.example.composenews.utils.Mapper
import com.example.composenews.utils.orEmpty

class ArticleDetailsMapper : Mapper<Content, ArticleDetail> {
    override fun map(item: Content): ArticleDetail = with(item) {
        return ArticleDetail(
            title = webTitle,
            url = id,
            image = if (elements.orEmpty().isNotEmpty()) elements.orEmpty()
                .first().assets.first().file else "",
            publishedAt = webPublicationDate,
            category = sectionName,
            author = if (tags.orEmpty().isNotEmpty()) tags.orEmpty().first().webTitle else "",
            authorImage = if (tags.orEmpty().isNotEmpty()) tags.orEmpty()
                .first().bylineImageUrl.orEmpty() else "",
            content = item.fields.body
        )
    }
}