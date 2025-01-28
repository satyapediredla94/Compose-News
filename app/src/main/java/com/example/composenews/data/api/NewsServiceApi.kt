package com.example.composenews.data.api

import com.example.composenews.BuildConfig
import com.example.composenews.data.model.articledetails.ArticleDetailsResponse
import com.example.composenews.data.model.toparticles.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsServiceApi {
    @GET("search")
    suspend fun getTopNews(
        @Query("api-key") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("page-size") pageSize: String = "50",
        @Query("show-elements") showElements: String = "image",
        @Query("show-tags") showTags: String = "contributor",
    ): Response<NewsResponse>

    @GET("{id}")
    suspend fun getArticleById(
        @Path("id") id: String,
        @Query("api-key") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("show-fields") showFields: String = "body",
        @Query("show-elements") showElements: String = "image",
        @Query("show-tags") showTags: String = "contributor"
    ): Response<ArticleDetailsResponse>

}