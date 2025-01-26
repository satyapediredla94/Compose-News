package com.example.composenews.viewmodel

sealed interface HomeScreenIntent {

    data object GetArticles : HomeScreenIntent

}