package com.example.sampleaudiorecorder

import android.content.ContentValues
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var recorder: MediaRecorder
    lateinit var player: MediaPlayer

    var filename = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button01)
        button.setOnClickListener {
            startRecording()
        }

        val sdcard = Environment.getExternalStorageDirectory().absolutePath
        filename = "$sdcard${File.separator}recorded.mp4"
    }

    private fun startRecording() {
        recorder = MediaRecorder()

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        recorder.setOutputFile(filename)

        recorder.prepare()
        recorder.start()
    }

    private fun stopRecording() {
        recorder.stop()
        recorder.release()

        val values = ContentValues(10)

        values.put(MediaStore.MediaColumns.TITLE, "Recorded")
        values.put(MediaStore.Audio.Media.ALBUM, "Audio Album")
        values.put(MediaStore.Audio.Media.ARTIST, "Mike")
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio")
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1)
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1)
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis()/1000)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4")
        values.put(MediaStore.Audio.Media.DATA, filename)

        val audioUri = contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)
    }
}
