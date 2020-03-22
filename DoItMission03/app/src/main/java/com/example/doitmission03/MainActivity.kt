package com.example.doitmission03

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView

class MainActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    var imageView2: ImageView? = null
    var imageNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        imageView2 = findViewById(R.id.imageView2)
    }

    fun onButtonClicked(v: View) {
        switch()
    }

    private fun switch() {
        if (imageNum == 0) {
            imageView?.visibility = View.INVISIBLE
            imageView2?.visibility = View.VISIBLE
            imageNum = 1
        } else {
            imageView?.visibility = View.VISIBLE
            imageView2?.visibility = View.INVISIBLE
            imageNum = 0
        }
    }
}
