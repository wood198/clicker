package com.ashley_laptop.clicker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ashley_laptop.clicker.CountRepository

class CountViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CountRepository(application.applicationContext)

    //retrieving the count from the repo. To be used in other activities
    fun getUserCount(name: String) = repository.getUserCount(name)

    //setting the count in the repo. To be used in other activities
    fun setUserCount(name: String, count: Long) = repository.setUserCount(name,count)
}