package com.example.samplelinearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class LayoutCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainLayout: LinearLayout = LinearLayout(this)
        mainLayout.orientation = LinearLayout.VERTICAL

        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        val button1 = Button(this)
        button1.text = "Button1"
        button1.layoutParams = params
        mainLayout.addView(button1)

        setContentView(mainLayout)
    }
}
