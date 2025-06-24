package com.example.network.domain.usecase

import com.example.network.data.model.CatImageModel
import com.example.network.data.repository.CatRepository
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(page: Int, limit: Int): List<CatImageModel> {
        return repository.fetchCatImages(page, limit).getOrThrow()
    }
}