package com.example.samplecustomviewdrawable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = CustomViewDrawable(this)
        setContentView(view)
    }
}
