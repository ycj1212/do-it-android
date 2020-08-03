package com.example.doitmission20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(this, 2)
        val adapter = Adapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            Thread(Runnable {
                /*  */
            })
        }
    }
}
