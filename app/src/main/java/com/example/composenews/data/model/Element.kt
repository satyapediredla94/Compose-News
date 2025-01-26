package com.example.composenews.data.model

data class Element(
    val assets: List<Asset>,
    val id: String,
    val relation: String,
    val type: String
)