package com.example.sampleyoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

private val API_KEY = ""
private val videoId = ""

class MainActivity : AppCompatActivity() {
    lateinit var playerView: YouTubePlayerView
    lateinit var player: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPlayer()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            playVideo()
        }
    }

    private fun initPlayer() {
        playerView = findViewById(R.id.playerView)
        playerView.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                player = p1!!
                player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener {
                    override fun onLoading() { }
                    override fun onLoaded(p0: String?) {
                        player.play()
                    }
                    override fun onAdStarted() { }
                    override fun onVideoStarted() { }
                    override fun onVideoEnded() { }
                    override fun onError(p0: YouTubePlayer.ErrorReason?) { }
                })
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) { }
        })
    }

    private fun playVideo() {
        if (player.isPlaying) {
            player.pause()
            player.cueVideo(videoId)
        }
    }
}
