package com.example.network.data.repository

import com.example.network.data.model.CatImageModel
import com.example.network.data.network.CatApiService
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val apiService: CatApiService
) {
    suspend fun fetchCatImages(page: Int, limit: Int): Result<List<CatImageModel>> {
        return try {
            val cats = apiService.getCatImages(limit, page)
            Result.success(cats)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}