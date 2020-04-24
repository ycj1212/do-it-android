package com.example.sampleparcelable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    var textView: TextView? = null

    companion object {
        const val KEY_SIMPLE_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        
        textView = textview
        val button = button
        button.setOnClickListener { 
            val intent = Intent()
            intent.putExtra("name", "mike")
            setResult(RESULT_OK, intent)
            finish()
        }
        
        val intent = intent
        processIntent(intent)
    }

    private fun processIntent(intent: Intent?) {
        if (intent != null) {
            val bundle: Bundle? = intent.extras
            val data: SimpleData? = bundle?.getParcelable(KEY_SIMPLE_DATA)
            if (intent != null) {
                textView?.text = "전달 받은 데이터\nNumber : ${data?.number}\nMessage : ${data?.message}"
            }
        }
    }
}
