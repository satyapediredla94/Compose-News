package com.example.composenews.ui.features.home.viewmodel

sealed interface HomeScreenIntent {

    data object GetArticles : HomeScreenIntent

}