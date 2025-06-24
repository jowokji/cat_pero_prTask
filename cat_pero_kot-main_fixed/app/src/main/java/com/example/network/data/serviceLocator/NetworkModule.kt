package com.example.network.data.serviceLocator

import com.example.network.data.network.CatApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object NetworkModule {
    private const val BASE_URL = "https://api.thecatapi.com/"

    private val okHttpClient = null

    private val retrofit = null

    val catApiService: CatApiService = null
}