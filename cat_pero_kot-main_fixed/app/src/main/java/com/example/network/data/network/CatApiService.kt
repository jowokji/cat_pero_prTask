package com.example.network.data.network

import com.example.network.data.model.CatImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET("v1/images/search")
    suspend fun getCatImages(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("order") order: String = "DESC"
    ): List<CatImageModel>
}