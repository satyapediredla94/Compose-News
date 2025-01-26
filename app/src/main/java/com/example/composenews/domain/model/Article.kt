package com.example.composenews.domain.model

data class Article(
    val title: String,
    val url: String,
    val image: String,
    val publishedAt: String,
    val author: String,
    val authorImage: String,
    val category: String
)
