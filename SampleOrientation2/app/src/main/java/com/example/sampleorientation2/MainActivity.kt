package com.example.sampleorientation2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast("onCreate 호출")
    }

    override fun onStart() {
        super.onStart()
        showToast("onStart 호출")
    }

    override fun onStop() {
        super.onStop()
        showToast("onStop 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("onDestroy 호출출")
   }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showToast("방향: ORIENTATION_LANDSCAPE")
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showToast("방향: ORIENTATION_PORTRAIT")
        }
    }

    private fun showToast(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
    }
}
