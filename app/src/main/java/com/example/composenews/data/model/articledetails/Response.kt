package com.example.composenews.data.model.articledetails

data class Response(
    val content: Content,
    val status: String,
    val total: Int,
    val userTier: String
)