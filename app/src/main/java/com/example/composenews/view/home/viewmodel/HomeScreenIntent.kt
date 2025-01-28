package com.example.composenews.view.home.viewmodel

sealed interface HomeScreenIntent {

    data object GetArticles : HomeScreenIntent

}