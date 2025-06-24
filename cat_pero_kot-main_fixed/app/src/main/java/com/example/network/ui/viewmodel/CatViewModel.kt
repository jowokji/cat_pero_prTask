package com.example.network.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.data.model.CatImageModel
import com.example.network.domain.usecase.GetCatImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object Loading : UiState()
    object Idle : UiState()
    data class Error(val throwable: Throwable) : UiState()
}

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
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val images = getCatImagesUseCase(currentPage, pageSize)
                _cats.value = _cats.value + images
                currentPage++
                _uiState.value = UiState.Idle
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            } finally {
                isLoadingMore = false
            }
        }
    }
}