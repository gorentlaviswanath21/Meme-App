package com.example.PS_MemeApp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiUtility {

    val  BASE_URL="https://api.imgflip.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}