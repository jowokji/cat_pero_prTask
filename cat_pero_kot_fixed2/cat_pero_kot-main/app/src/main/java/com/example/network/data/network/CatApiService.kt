package com.example.network.data.network

import com.example.network.data.model.CatImageModel

// NEVER DO IT IN PROD
private const val API_KEY = "your_api_key_here"

interface CatApiService {
    suspend fun getRandomCats(limit: Int): List<CatImageModel>
}