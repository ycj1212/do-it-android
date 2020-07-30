package com.example.doitmission17

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var selected = 0
    lateinit var panel1: Panel
    lateinit var panel2: Panel
    lateinit var animationIn: Animation
    lateinit var animationOut: Animation
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        panel1 = Panel(this)
        panel2 = Panel(this)
        val container: FrameLayout = findViewById(R.id.container)
        animationIn = AnimationUtils.loadAnimation(this, R.anim.translate_in)
        animationOut = AnimationUtils.loadAnimation(this, R.anim.translate_out)
        val thread = BackgroundThead()

        panel1.name.text = "양철주"
        panel1.phone.text = "010-2277-4106"
        panel1.city.text = "평택시"
        container.addView(panel1)

        panel2.name.text = "양단비"
        panel2.phone.text = "010-2518-4106"
        panel2.city.text = "안정리"
        container.addView(panel2)

        thread.start()
    }

    inner class BackgroundThead : Thread() {
        override fun run() {
            while (true) {
                handler.post {
                    if (selected == 0) {
                        panel1.startAnimation(animationOut)
                        panel2.startAnimation(animationIn)
                    } else {
                        panel1.startAnimation(animationIn)
                        panel2.startAnimation(animationOut)
                    }
                }

                selected++
                if (selected > 1) {
                    selected = 0
                }
                sleep(5000)
            }
        }
    }

    inner class Panel : LinearLayout {
        lateinit var name: TextView
        lateinit var phone: TextView
        lateinit var city: TextView

        constructor(context: Context) : super (context) { init() }
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { init() }

        private fun init() {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            inflater.inflate(R.layout.client_information, this, true)

            name = findViewById(R.id.name)
            phone = findViewById(R.id.phone)
            city = findViewById(R.id.city)
        }
    }
}
