package com.example.composenews.domain.repository

import com.example.composenews.domain.model.Article
import com.example.composenews.domain.model.ArticleDetail
import kotlinx.coroutines.flow.Flow

interface NewsApi {
    suspend fun getFeaturedNews(): Flow<Result<List<Article>>>
    suspend fun getArticleDetails(articleId: String): Flow<Result<ArticleDetail>>
}