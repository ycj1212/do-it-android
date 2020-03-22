package com.example.sampleframelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    var imageView2: ImageView? = null

    var imageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        imageView2 = findViewById(R.id.imageView2)
    }

    fun onButton1Clicked(v: View) {
        changeImage()
    }

    private fun changeImage() {
        if (imageIndex == 0) {
            imageView?.visibility = View.VISIBLE
            imageView2?.visibility = View.INVISIBLE
            imageIndex = 1
        } else {
            imageView?.visibility = View.INVISIBLE
            imageView2?.visibility = View.VISIBLE
            imageIndex = 0
        }
    }
}
