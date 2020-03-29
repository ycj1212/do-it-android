package com.example.sampleintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

const val REQUEST_CODE_MENU = 101

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent: Intent = Intent(applicationContext, MenuActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_MENU)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(this, "onActivityResult 메소드 호출됨. 요청 코드 : $requestCode, 결과 코드 : $resultCode", Toast.LENGTH_LONG).show()

            if (resultCode == RESULT_OK) {
                val name = data?.getStringExtra("name")
                Toast.makeText(this, "응답으로 전달된 name : $name", Toast.LENGTH_LONG).show()
            }
        }
    }
}
