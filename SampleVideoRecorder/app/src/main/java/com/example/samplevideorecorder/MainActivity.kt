package com.example.samplevideorecorder

import android.content.ContentValues
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {
    lateinit var player: MediaPlayer
    lateinit var recorder: MediaRecorder

    lateinit var holder: SurfaceHolder

    var filename = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val surface = SurfaceView(this)
        holder = surface.holder
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        val frame: FrameLayout = findViewById(R.id.container)
        frame.addView(surface)

        val button: Button = findViewById(R.id.button01)
        button.setOnClickListener {
            startRecording()
        }
    }

    private fun startRecording() {
        recorder = MediaRecorder()

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT)
        recorder.setOutputFile(filename)

        recorder.setPreviewDisplay(holder.surface)
        recorder.prepare()
        recorder.start()
    }

    private fun stopRecording() {
        recorder.stop()
        recorder.reset()
        recorder.release()

        val values = ContentValues(10)

        values.put(MediaStore.MediaColumns.TITLE, "RecordedVideo")
        values.put(MediaStore.Audio.Media.ALBUM, "Video Album")
        values.put(MediaStore.Audio.Media.ARTIST, "Mike")
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Video")
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis()/1000)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        values.put(MediaStore.Audio.Media.DATA, filename)

        val videoUri = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)

        sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, videoUri))
    }
}
