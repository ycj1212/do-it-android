package com.example.sampleaudioplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

const val AUDIO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_audio.mp3"

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button01)
        button.setOnClickListener {
            playAudio(AUDIO_URL)
            Toast.makeText(this, "음악 파일 재생 시작됨.", Toast.LENGTH_LONG).show()
        }

        val button2: Button = findViewById(R.id.button02)
        button2.setOnClickListener {
            mediaPlayer.stop()
            Toast.makeText(this, "음악 파일 재생 중지됨.", Toast.LENGTH_LONG).show()
        }

        val button3: Button = findViewById(R.id.button03)
        button3.setOnClickListener {
            position = mediaPlayer.currentPosition
            mediaPlayer.pause()
            Toast.makeText(this, "음악 파일 재생 일시정지됨.", Toast.LENGTH_LONG).show()
        }

        val button4: Button = findViewById(R.id.button04)
        button4.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                mediaPlayer.seekTo(position)
                Toast.makeText(this, "음악 파일 재생 재시작됨.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun playAudio(url: String) {
        killMediaPlayer()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        killMediaPlayer()
    }

    private fun killMediaPlayer() {
        mediaPlayer.release()   // MediaPlayer 객체의 리소스 해제
    }
}
