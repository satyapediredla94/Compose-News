package com.example.composenews.utils

interface Mapper<T, P>  {
    fun map(item: T) : P
}