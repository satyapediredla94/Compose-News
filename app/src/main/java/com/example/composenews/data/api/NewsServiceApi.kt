package com.example.composenews.data.api

import com.example.composenews.BuildConfig
import com.example.composenews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {
    @GET("search")
    suspend fun getTopNews(
        @Query("q") query: String = "United States",
        @Query("api-key") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("page-size") pageSize: String = "50",
        @Query("show-elements") showElements: String = "image",
        @Query("show-tags") showTags: String = "contributor",
    ): Response<NewsResponse>
}