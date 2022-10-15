package com.example.PS_MemeApp.api

import com.example.PS_MemeApp.model.Jokes
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/get_memes")
    suspend fun getJokes():Response<Jokes>
}