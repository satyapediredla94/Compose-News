package com.example.composenews.domain.repository

import com.example.composenews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsApi {
    suspend fun getFeaturedNews(): Flow<Result<List<Article>>>
}