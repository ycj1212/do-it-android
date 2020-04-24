package com.example.doitmission08

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sales.*

class SalesActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        println("onCreate 호출됨")

        val button_menu = button_to_menu
        button_menu.setOnClickListener(this)
        val button_login = button_to_login
        button_login.setOnClickListener(this)
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
        Log.d("Sales", s)
    }

    override fun onClick(v: View) {
        val intent = Intent()
        intent.putExtra("name", "매출 관리")
        when (v.id) {
            R.id.button_to_menu -> setResult(1, intent)
            R.id.button_to_login -> setResult(2, intent)
        }
        finish()
    }
}