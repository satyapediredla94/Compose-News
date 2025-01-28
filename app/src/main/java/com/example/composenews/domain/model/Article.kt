package com.example.composenews.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val url: String,
    val image: String,
    val publishedAt: String,
    val author: String,
    val authorImage: String,
    val category: String
) : Parcelable
