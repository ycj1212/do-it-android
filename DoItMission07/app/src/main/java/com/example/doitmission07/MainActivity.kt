package com.example.doitmission07

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = button_login
        button.setOnClickListener {
            val intent = Intent(applicationContext, MenuActivity::class.java)
            startActivityForResult(intent, 101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {
            Toast.makeText(this, "Request Code: $requestCode, Result Code: $resultCode", Toast.LENGTH_LONG).show()

            if (resultCode == Activity.RESULT_OK) {
                val name = data?.getStringExtra("name")
                Toast.makeText(this, "Name: $name", Toast.LENGTH_LONG).show()
            }
        }
    }
}
