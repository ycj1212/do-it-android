package com.example.sampletweenanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)

        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var animation: Animation? = null
        when (v?.id) {
            R.id.button -> animation = AnimationUtils.loadAnimation(applicationContext, R.anim.scale)
            R.id.button2 -> animation = AnimationUtils.loadAnimation(applicationContext, R.anim.scale2)
            R.id.button3 -> animation = AnimationUtils.loadAnimation(applicationContext, R.anim.translate)
            R.id.button4 -> animation = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
            R.id.button5 -> animation = AnimationUtils.loadAnimation(applicationContext, R.anim.alpha)
        }
        v?.startAnimation(animation)
    }
}
