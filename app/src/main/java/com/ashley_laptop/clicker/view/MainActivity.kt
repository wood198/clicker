package com.ashley_laptop.clicker.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ashley_laptop.clicker.R
import com.ashley_laptop.clicker.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //late initializing the count view model, initializing the counter
    private lateinit var countViewModel: CountViewModel
    private var counter: Long = 0

    //creating a username function that takes in the string entered on the login page
    private fun getUserName() = intent.extras?.get("username").toString().trim()

//    fun getStore() = getPreferences(Context.MODE_PRIVATE)
//    var COUNTER_KEY: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val name = intent.extras?.get("username").toString().trim()
//        COUNTER_KEY = name

//        if (savedInstanceState != null) {
//            updateCounter(savedInstanceState.getLong(COUNTER_KEY, 0))
//        }else if (getStore().contains(COUNTER_KEY)) {
//            updateCounter(getStore().getLong(COUNTER_KEY, 0))
//        }

        //gets the info from countViewModel and gets the count from the user and updates the counter
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUserName()).observe(this,
            androidx.lifecycle.Observer { updateCounter(it) })

        var backgroundCounter = counter

        //when the button is clicked...
        myButton.setOnClickListener {
            //counter updates
            counter++
            clicks.text = "Clicks: " + counter.toString()

            //background changes every 30 clicks
            if(counter == backgroundCounter + 30) {
                var randomInteger = (1..4).shuffled().first()
                if (randomInteger == 1) {
                    background.setImageResource(R.drawable.fall)
                    backgroundCounter += 30
                } else if (randomInteger == 2) {
                    background.setImageResource(R.drawable.winter)
                    backgroundCounter += 30
                } else if (randomInteger == 3) {
                    background.setImageResource(R.drawable.spring)
                    backgroundCounter += 30
                } else if (randomInteger == 4) {
                    background.setImageResource(R.drawable.summer)
                    backgroundCounter += 30
                }
            }

            //sets the user account in storage so it will save
            countViewModel.setUserCount(getUserName(), counter + 1)
        }
    }

    //update the counter on the screen
    private fun updateCounter(count: Long) {
        counter = count
        clicks.text = "Score ${counter.toString()}"
    }

//    override fun onPause() {
//        super.onPause()
//        getStore().edit().putLong(COUNTER_KEY, counter).apply()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.run {
//            putLong(COUNTER_KEY, counter)
//        }
//
//        super.onSaveInstanceState(outState)
//    }
}
