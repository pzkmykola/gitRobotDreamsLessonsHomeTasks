package com.example.gitrootdreamslessonshometasks

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Application {
    @Provides
    @Singleton
    fun getApiClient(): Retrofit? = ApiClient().client
    @Provides
    @Singleton
    fun getRepository(apiClient: ApiClient) = Repository(apiClient)
}