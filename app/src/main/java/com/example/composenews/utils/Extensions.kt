package com.example.composenews.utils

fun String?.orEmpty() = this ?: ""

fun Int?.orZero() = this ?: 0

fun Long?.orZero() = this ?: 0L

fun Float?.orZero() = this ?: 0f