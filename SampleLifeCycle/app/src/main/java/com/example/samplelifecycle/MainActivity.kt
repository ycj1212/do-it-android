package com.example.samplelifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var nameInput: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("onCreate 호출됨")
        val button = button
        onClick(button)
    }

    override fun onStart() {
        super.onStart()
        println("onStart 호출됨")
    }

    override fun onStop() {
        super.onStop()
        println("onStop 호출됨")
    }

    override fun onPause() {
        super.onPause()
        println("onPause 호출됨")
        saveState()
    }

    override fun onResume() {
        super.onResume()
        println("onResume 호출됨")
        restoreState()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy 호출됨")
    }

    private fun onClick(view: View) {
        val intent = Intent(applicationContext, MenuActivity::class.java)
        startActivity(intent)
    }

    private fun println(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show()
        Log.d("Main", data)
    }

    private fun restoreState() {
        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        if ((pref != null) && (pref.contains("name"))) {
            val name = pref.getString("name", "")
            nameInput?.setText(name)
        }
    }

    private fun saveState() {
        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("name", nameInput?.text.toString())
        editor.commit()
    }

    private fun clearState() {
        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}
