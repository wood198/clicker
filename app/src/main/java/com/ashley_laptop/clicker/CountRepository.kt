package com.ashley_laptop.clicker

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences

class CountRepository(context: Context) {
    //create the sharedpreferences
    private val preferences: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences

    //initializing sharedpreferences in private mode
    init {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }

    //function to set the user count in storage
    fun setUserCount(name: String, count: Long) {
        preferences.edit().putLong(name, count).apply()
    }

    //function to set the user name in storage
    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name),0L)) {it[name]}

    //create the constant value for storage
    companion object {
        private const val PREFS = "clickCounts"
    }
}