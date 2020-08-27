package com.example.samplemultitouch

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container: LinearLayout = findViewById(R.id.container)
        val res = resources
        val bitmap = BitmapFactory.decodeResource(res, R.drawable.beach)

        val view = ImageDisplayView(this)
        view.setImageData(bitmap)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        container.addView(view, params)
    }
}
