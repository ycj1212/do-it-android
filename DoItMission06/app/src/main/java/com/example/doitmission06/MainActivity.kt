package com.example.doitmission06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    var seekBar: SeekBar? = null
    var progressBar: ProgressBar? = null
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar)
        progressBar = findViewById(R.id.progressBar)
        editText = findViewById(R.id.editText)

        var value = 0

        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value = progress
            }
        })

        progressBar?.progress = value
        editText?.setText(value)
    }
}
