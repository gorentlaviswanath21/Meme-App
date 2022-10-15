package com.example.PS_MemeApp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.PS_MemeApp.api.ApiInterface
import com.example.PS_MemeApp.model.Data
import com.example.PS_MemeApp.model.Jokes
import com.example.PS_MemeApp.model.Meme
import com.example.PS_MemeApp.room.MemeDatabase
import com.example.PS_MemeApp.util.MyUtils

class MemesRepository(
    private val apiInterface: ApiInterface,
    private val memeDatabase: MemeDatabase,
    private val applicationContext: Context
) {

    private  val memesLiveData= MutableLiveData<Jokes>()

    val memes:LiveData<Jokes>
    get()=memesLiveData

    suspend fun getMemes(){
        if(MyUtils.isInternetAvailable(applicationContext)){
            val result=apiInterface.getJokes()
            if(result.body()!=null){
                memeDatabase.memeDao().insertmemes(result.body()!!.data.memes)
               // memesLiveData.postValue(result.body())
            }
            val memes=memeDatabase.memeDao().getMemes()
            val memeList= Jokes(Data(memes),true)
            memesLiveData.postValue(memeList)

        }
        else{
           // Toast.makeText(applicationContext,"Make sure your connectivity",Toast.LENGTH_SHORT).show()
            val memes=memeDatabase.memeDao().getMemes()
            val memeList= Jokes(Data(memes),true)
            memesLiveData.postValue(memeList)
        }

    }
    suspend fun deleteMeme(meme : Meme){
        memeDatabase.memeDao().delete(meme)
    }
}