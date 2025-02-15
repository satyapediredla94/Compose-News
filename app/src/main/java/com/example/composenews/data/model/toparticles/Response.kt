package com.example.composenews.data.model.toparticles

data class Response(
    val currentPage: Int,
    val orderBy: String,
    val pageSize: Int,
    val pages: Int,
    val results: List<Result>,
    val startIndex: Int,
    val status: String,
    val total: Int,
    val userTier: String
)