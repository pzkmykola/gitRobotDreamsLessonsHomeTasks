package com.example.gitrootdreamslessonshometasks

import retrofit2.Response
import javax.inject.Singleton

@Singleton
class Repository(val apiClient: ApiClient) {
    suspend fun getCurrencyByName(name:String): Response<BitcoinResponse> {
        val apiInterface = apiClient.client.create(ApiInterface::class.java)
        return apiInterface.getCryptoByName(name)
    }
}