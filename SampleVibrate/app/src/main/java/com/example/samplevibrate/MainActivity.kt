package com.example.samplevibrate

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button01)
        button.setOnClickListener {
            val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, 10))
            } else {
                vibrator.vibrate(1000)
            }
        }

        val button2: Button = findViewById(R.id.button02)
        button2.setOnClickListener {
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, uri)
            ringtone.play()
        }

        val button3: Button = findViewById(R.id.button03)
        button3.setOnClickListener {
            val player = MediaPlayer.create(applicationContext, R.raw.beep)
            player.start()
        }
    }
}
