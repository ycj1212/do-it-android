package com.example.samplevideoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

const val VIDEO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_video.mp4"

class MainActivity : AppCompatActivity() {
    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)

        val mc = MediaController(this)
        videoView.setMediaController(mc)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            videoView.setVideoURI(Uri.parse(VIDEO_URL))
            videoView.requestFocus()
            videoView.start()
        }
    }
}
