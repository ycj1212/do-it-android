package com.example.sampleactionbar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val v = menu?.findItem(R.id.menu_search)?.actionView
        if (v != null) {
            val editText = v.findViewById<EditText>(R.id.editText)

            if (editText != null) {
                editText.setOnEditorActionListener { v, actionId, event ->
                    Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_LONG).show()
                    true
                }
            }
        }

        return true
    }
}
