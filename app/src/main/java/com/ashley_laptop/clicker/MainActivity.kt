package com.ashley_laptop.clicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ashley_laptop.clicker.util.rotate90
import com.ashley_laptop.clicker.util.toggleVisibility
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY, 0)
        }

        myButton.setOnClickListener {
            counter++
            clicks.text = "Clicks: " + counter.toString()
            someImage.rotate90()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(COUNTER_KEY, counter)
        }

        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val COUNTER_KEY = "counter key"
    }

}
