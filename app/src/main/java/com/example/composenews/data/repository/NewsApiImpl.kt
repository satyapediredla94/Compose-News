package com.example.composenews.data.repository

import com.example.composenews.data.ResultMapper
import com.example.composenews.data.api.NewsServiceApi
import com.example.composenews.domain.model.Article
import com.example.composenews.domain.repository.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsApiImpl(
    private val newsServiceApi: NewsServiceApi,
    private val itemMapper: ResultMapper
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
}