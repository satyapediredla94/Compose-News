package com.example.composenews.di

import com.example.composenews.data.ResultMapper
import com.example.composenews.data.api.NewsServiceApi
import com.example.composenews.data.repository.NewsApiImpl
import com.example.composenews.domain.repository.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofitService(): NewsServiceApi {
        return Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMapper() : ResultMapper = ResultMapper()

    @Provides
    @Singleton
    fun providesNewsApi(
        newsServiceApi: NewsServiceApi,
        resultMapper: ResultMapper
    ): NewsApi = NewsApiImpl(newsServiceApi, resultMapper)

}