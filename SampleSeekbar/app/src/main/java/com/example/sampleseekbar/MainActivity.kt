package com.example.sampleseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val seekBar: SeekBar = findViewById(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setBrightness(progress)
                textView?.text = "변경된 값: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setBrightness(value: Int) {
        var valueTemp = 0
        if (value < 10) {
            valueTemp = 10
        } else if (value > 100) {
            valueTemp = 100
        }

        val params: WindowManager.LayoutParams = window.attributes
        params.screenBrightness = (valueTemp / 100).toFloat()
        window.attributes = params
    }
}
