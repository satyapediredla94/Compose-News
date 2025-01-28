package com.example.composenews.data.repository

import com.example.composenews.data.ArticleDetailsMapper
import com.example.composenews.data.ArticleMapper
import com.example.composenews.data.api.NewsServiceApi
import com.example.composenews.domain.model.Article
import com.example.composenews.domain.model.ArticleDetail
import com.example.composenews.domain.repository.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsApiImpl(
    private val newsServiceApi: NewsServiceApi,
    private val itemMapper: ArticleMapper,
    private val articleDetailMapper: ArticleDetailsMapper
) : NewsApi {
    override suspend fun getFeaturedNews(): Flow<Result<List<Article>>> = flow {
        val response = newsServiceApi.getTopNews()
        if (response.isSuccessful) {
            val articles =
                response.body()?.response?.results.orEmpty().filterNot { it.elements.isEmpty() }
                    .map { itemMapper.map(it) }
            emit(Result.success(articles))
        } else {
            emit(Result.failure(Exception(response.message())))
        }
    }

    override suspend fun getArticleDetails(articleId: String): Flow<Result<ArticleDetail>> = flow {
        val response = newsServiceApi.getArticleById(id = articleId)
        if (response.isSuccessful) {
            val articleDetails =
                response.body()?.response?.content?.let { articleDetailMapper.map(it) }
            if (articleDetails == null) {
                emit(Result.failure(Exception("Article details not found")))
            } else {
                emit(Result.success(articleDetails))
            }
        } else {
            emit(Result.failure(Exception(response.message())))
        }
    }
        .flowOn(Dispatchers.IO)
}