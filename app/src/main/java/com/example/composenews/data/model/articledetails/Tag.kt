package com.example.composenews.data.model.articledetails

data class Tag(
    val apiUrl: String,
    val bio: String,
    val bylineImageUrl: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val references: List<Any>,
    val type: String,
    val webTitle: String,
    val webUrl: String
)