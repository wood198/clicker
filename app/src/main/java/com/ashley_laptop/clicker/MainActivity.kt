package com.ashley_laptop.clicker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ashley_laptop.clicker.util.rotate90
import com.ashley_laptop.clicker.util.toggleVisibility
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var counter: Long = 0
    fun getStore() = getPreferences(Context.MODE_PRIVATE)
    var COUNTER_KEY: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = intent.extras?.get("username").toString().trim()
        COUNTER_KEY = name

        if (savedInstanceState != null) {
            updateCounter(savedInstanceState.getLong(COUNTER_KEY, 0))
        }else if (getStore().contains(COUNTER_KEY)) {
            updateCounter(getStore().getLong(COUNTER_KEY, 0))
        }

        myButton.setOnClickListener {
            counter++
            clicks.text = "Clicks: " + counter.toString()
            someImage.rotate90()
        }
    }

    private fun updateCounter(count: Long) {
        counter = count
        clicks.text = "Score ${counter.toString()}"
    }

    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(COUNTER_KEY, counter).apply()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putLong(COUNTER_KEY, counter)
        }

        super.onSaveInstanceState(outState)
    }

//    companion object {
//        //need name key to increase in number as new usernames are entered
//        //need counter key to increase in number as new usernames are entered
//        private const val COUNTER_KEY = "name"
//    }

}
