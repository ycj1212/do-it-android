package com.example.samplescrollview

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView

class MainActivity : AppCompatActivity() {
    var scrollView: ScrollView? = null
    var imageView: ImageView? = null
    var bitmap: BitmapDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scrollView)
        imageView = findViewById(R.id.imageView)
        scrollView?.isHorizontalScrollBarEnabled = true

        val res: Resources = resources
        bitmap = res.getDrawable(R.drawable.image01) as BitmapDrawable
        val bitmapWidth = bitmap?.intrinsicWidth
        val bitmapHeight = bitmap?.intrinsicHeight

        imageView?.setImageDrawable(bitmap)
        imageView?.layoutParams?.width = bitmapWidth
        imageView?.layoutParams?.height = bitmapHeight
    }

    fun onButton1Clicked(v: View) {
        changeImage()
    }

    private fun changeImage() {
        val res: Resources = resources
        bitmap = res.getDrawable(R.drawable.image02) as BitmapDrawable
        val bitmapWidth = bitmap?.intrinsicWidth
        val bitmapHeight = bitmap?.intrinsicHeight

        imageView?.setImageDrawable(bitmap)
        imageView?.layoutParams?.width = bitmapWidth
        imageView?.layoutParams?.height = bitmapHeight
    }
}
