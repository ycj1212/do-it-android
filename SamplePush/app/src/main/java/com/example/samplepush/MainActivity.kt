package com.example.samplepush

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            val newToken = it.token

            println("등록 id : $newToken")
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val instanceId = FirebaseInstanceId.getInstance().id
            println("확인된 인스턴스 id : $instanceId")
        }
    }

    private fun println(data: String) {
        textView2.append("$data\n")
    }
}
