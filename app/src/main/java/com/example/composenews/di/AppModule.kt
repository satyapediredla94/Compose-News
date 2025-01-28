package com.example.composenews.di

import com.example.composenews.data.ArticleDetailsMapper
import com.example.composenews.data.ArticleMapper
import com.example.composenews.data.api.NewsServiceApi
import com.example.composenews.data.repository.NewsApiImpl
import com.example.composenews.domain.repository.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    internal object UrlDecoderInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            var url: String = original.url.toString()
            url = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8.name())
            val requestBuilder: Request.Builder = original.newBuilder().url(url)
            val request: Request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesRetrofitService(): NewsServiceApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val clientInterceptor = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(UrlDecoderInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientInterceptor)
            .build()
            .create(NewsServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun providesArticleMapper(): ArticleMapper = ArticleMapper()

    @Provides
    @Singleton
    fun providesArticleDetailsMapper(): ArticleDetailsMapper = ArticleDetailsMapper()

    @Provides
    @Singleton
    fun providesNewsApi(
        newsServiceApi: NewsServiceApi,
        articleMapper: ArticleMapper,
        articleDetailsMapper: ArticleDetailsMapper
    ): NewsApi = NewsApiImpl(newsServiceApi, articleMapper, articleDetailsMapper)

}