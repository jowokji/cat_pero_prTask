package com.example.network.di

import com.example.network.data.repository.CatRepository
import com.example.network.data.network.CatApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCatRepository(apiService: CatApiService): CatRepository =
        CatRepository(apiService)
}