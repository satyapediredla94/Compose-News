package com.example.composenews.ui.features.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenews.domain.model.Article
import com.example.composenews.domain.repository.NewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val api: NewsApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    fun handleIntent(homeScreenIntent: HomeScreenIntent) {
        when (homeScreenIntent) {
            HomeScreenIntent.GetArticles -> getArticles()
        }
    }

    private fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            api.getFeaturedNews().collectLatest { result ->
                result
                    .onSuccess { articles ->
                        _uiState.update {
                            it.copy(isLoading = false, articles = articles)
                        }
                    }
                    .onFailure { exception ->
                        _uiState.update {
                            it.copy(isLoading = false, errorMessages = exception.localizedMessage)
                        }
                    }
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val articles: List<Article> = emptyList(),
        val errorMessages: String? = ""
    )

}