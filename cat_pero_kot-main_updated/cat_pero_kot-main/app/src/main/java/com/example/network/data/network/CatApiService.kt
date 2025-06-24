
package com.example.network.data.network

import com.example.network.data.model.CatImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getCatImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10,
        @Query("order") order: String = "DESC"
    ): List<CatImageModel>
}
