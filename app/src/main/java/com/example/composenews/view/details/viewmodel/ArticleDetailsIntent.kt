package com.example.composenews.view.details.viewmodel

import com.example.composenews.domain.model.Article

sealed interface ArticleDetailsIntent {
    data class GetArticleDetails(val article: Article?) : ArticleDetailsIntent
}