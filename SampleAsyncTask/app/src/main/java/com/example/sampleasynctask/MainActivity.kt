package com.example.sampleasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    private lateinit var task: BackgroundTask
    var value: Int? = null

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        val button: Button = findViewById(R.id.button1)
        button.setOnClickListener {
            // 태스크 객체 만들어 실행
            task = BackgroundTask()
            task.execute()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            task.cancel(true)
        }
    }

    inner class BackgroundTask : AsyncTask<Int, Int, Int>() {
        override fun onPreExecute() {
            value = 0
            progressBar.progress = value!!
        }

        // 태스크 객체 안에서 백그라운드 작업 수행
        override fun doInBackground(vararg params: Int?): Int {
            while (!isCancelled) {
                value = value?.plus(1)
                if (value!! >= 100) {
                    break
                } else {
                    publishProgress(value)
                }

                sleep(100)
            }

            return value!!
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values[0]!!
        }

        override fun onPostExecute(result: Int?) {
            progressBar.progress = 0
        }

        override fun onCancelled() {
            progressBar.progress = 0
        }
    }
}
