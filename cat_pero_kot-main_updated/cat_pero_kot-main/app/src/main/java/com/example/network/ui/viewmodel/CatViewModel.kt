
package com.example.network.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.data.model.CatImageModel
import domain.GetCatImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : ViewModel() {

    private val _cats = MutableStateFlow<List<CatImageModel>>(emptyList())
    val cats: StateFlow<List<CatImageModel>> = _cats

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private var currentPage = 0
    private val pageSize = 10
    private var isLoadingMore = false

    init {
        fetchCats()
    }

    fun fetchCats() {
        if (isLoadingMore) return
        isLoadingMore = true
        viewModelScope.launch {
            if (currentPage == 0) _uiState.value = UiState.Loading
            try {
                val result = getCatImagesUseCase(currentPage, pageSize)
                result.onSuccess { images ->
                    if (currentPage == 0) {
                        _cats.value = images
                    } else {
                        _cats.update { it + images }
                    }
                    currentPage++
                    _uiState.value = UiState.Success(_cats.value)
                }.onFailure { e ->
                    _uiState.value = UiState.Error(e.message ?: "Unknown error")
                }
            } finally {
                isLoadingMore = false
            }
        }
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val cats: List<CatImageModel>) : UiState()
    data class Error(val message: String) : UiState()
}
