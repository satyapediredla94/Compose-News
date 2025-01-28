package com.example.composenews.domain.model

data class ArticleDetail(
    val url: String,
    val title: String,
    val content: String,
    val author: String,
    val authorImage: String,
    val publishedAt: String,
    val image: String,
    val category: String
)
