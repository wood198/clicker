package com.ashley_laptop.clicker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ashley_laptop.clicker.ApiRepository

class GifViewModel(application: Application) : AndroidViewModel(application){
    private val repository = ApiRepository(application.applicationContext)

    fun getRandomGif(tag: String) = repository.getRandomGif(tag)

}