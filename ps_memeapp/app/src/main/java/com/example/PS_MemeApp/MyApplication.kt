package com.example.PS_MemeApp

import android.app.Application
import com.example.PS_MemeApp.api.ApiInterface
import com.example.PS_MemeApp.api.apiUtility
import com.example.PS_MemeApp.repository.MemesRepository
import com.example.PS_MemeApp.room.MemeDatabase

class MyApplication:Application() {

    lateinit var memesRepository: MemesRepository
    override fun onCreate() {

        super.onCreate()
        val apiInterface= apiUtility.getInstance().create(ApiInterface::class.java)
        val database= MemeDatabase.getDatabase(applicationContext)
        memesRepository= MemesRepository(apiInterface,database,applicationContext)
    }

}