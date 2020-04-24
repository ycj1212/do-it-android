package com.example.doitmission08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("onCreate 호출됨")

        val edittext01 = edittext01
        val edittext02 = edittext02
        val button = button_login
        button.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            if (edittext01.text.toString() != "" && edittext02.text.toString() != "") {
                startActivityForResult(intent, 101)
            }
            else {
                Toast.makeText(this, "입력하세요", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("onStart 호출됨")
    }

    override fun onResume() {
        super.onResume()
        println("onResume 호출됨")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart 호출됨")
    }

    override fun onPause() {
        super.onPause()
        println("onPause 호출됨")
    }

    override fun onStop() {
        super.onStop()
        println("onStop 호출됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy 호출됨")
    }

    private fun println(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
        Log.d("Main", s)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {

            if (resultCode == 0) {

            }
        }
    }
}
