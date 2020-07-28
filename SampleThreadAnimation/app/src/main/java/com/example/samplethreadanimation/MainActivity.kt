package com.example.samplethreadanimation

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    val drawableList = ArrayList<Drawable>()
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val res = resources
        drawableList.add(res.getDrawable(R.drawable.face1))
        drawableList.add(res.getDrawable(R.drawable.face2))
        drawableList.add(res.getDrawable(R.drawable.face3))
        drawableList.add(res.getDrawable(R.drawable.face4))
        drawableList.add(res.getDrawable(R.drawable.face5))

        imageView = findViewById(R.id.imageView)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val thread = AnimThread()
            thread.start()
        }
    }

    inner class AnimThread : Thread() {
        override fun run() {
            var index = 0
            for (i in 0 until 100) {
                val drawable = drawableList[index]
                index += 1
                if (index > 4) {
                    index = 0
                }

                handler.post {
                    imageView.setImageDrawable(drawable)
                }

                sleep(1000)
            }
        }
    }
}
