package com.example.composenews.ui.features.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composenews.domain.model.ArticleDetail
import com.example.composenews.domain.repository.NewsApi
import com.example.composenews.utils.orEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val newsApi: NewsApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun handleIntent(intent: ArticleDetailsIntent) {
        when (intent) {
            is ArticleDetailsIntent.GetArticleDetails -> loadArticle(intent.article?.url.orEmpty())
        }
    }

    private fun loadArticle(articleId: String) {
        if (articleId.isNotEmpty()) {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch(Dispatchers.IO) {
                newsApi.getArticleDetails(articleId).collectLatest { result ->
                    result
                        .onSuccess { article ->
                            _uiState.update {
                                it.copy(
                                    article = article,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                        .onFailure { throwable ->
                            _uiState.update {
                                it.copy(
                                    article = null,
                                    isLoading = false,
                                    error = throwable.message
                                )
                            }
                        }
                }
            }
        }
    }

    data class UiState(
        val article: ArticleDetail? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    )

}