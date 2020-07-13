package com.example.samplepagesliding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout

var isPageOpen = false

var translateLeftAnim: Animation? = null
var translateRightAnim: Animation? = null

var page: LinearLayout? = null
var button: Button? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        page = findViewById(R.id.page)
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left)
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right)

        val animListener: SlidingPageAnimationListener = SlidingPageAnimationListener()
        translateLeftAnim?.setAnimationListener(animListener)
        translateRightAnim?.setAnimationListener(animListener)

        button = findViewById(R.id.button)
        button?.setOnClickListener {
            if (isPageOpen) {
                page?.startAnimation(translateRightAnim)
            } else {
                page?.visibility = View.VISIBLE
                page?.startAnimation(translateLeftAnim)
            }
        }
    }

    private class SlidingPageAnimationListener : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation?) {
            if (isPageOpen) {
                page?.visibility = View.INVISIBLE
                button?.text = "Open"
                isPageOpen = false
            } else {
                button?.text = "Close"
                isPageOpen = true
            }
        }
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationRepeat(animation: Animation?) {}
    }
}
