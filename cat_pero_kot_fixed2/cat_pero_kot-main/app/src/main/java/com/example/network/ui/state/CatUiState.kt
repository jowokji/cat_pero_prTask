package com.example.network.ui.state

import com.example.network.data.model.CatImageModel

data class CatUiState(
    val cats: List<CatImageModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)