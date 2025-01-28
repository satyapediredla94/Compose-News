package com.example.composenews.data.model.articledetails

data class Content(
    val apiUrl: String,
    val elements: List<Element>?,
    val fields: Fields,
    val id: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String,
    val sectionId: String,
    val sectionName: String,
    val tags: List<Tag>?,
    val type: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String
)