package com.example.PS_MemeApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.PS_MemeApp.model.Jokes
import com.example.PS_MemeApp.model.Meme
import com.example.PS_MemeApp.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesViewModel(private val memesRepository: MemesRepository):ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO){
            memesRepository.getMemes()
        }
    }

    val memes:LiveData<Jokes>
    get()=memesRepository.memes

    fun deleteMeme(meme : Meme) = viewModelScope.launch {
        memesRepository.deleteMeme(meme)
    }
}