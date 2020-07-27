package com.example.doitmisstion13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var textview: TextView? = null
    var edittext_name: EditText? = null
    var edittext_date_of_birth: EditText? = null
    var edittext_phone_number: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview = findViewById(R.id.textview02)
        edittext_name = findViewById(R.id.edittext01)
        edittext_date_of_birth = findViewById(R.id.edittext02)
        edittext_phone_number = findViewById(R.id.edittext03)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter: ClientAdapter = ClientAdapter()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val name: String = edittext_name?.text.toString()
            val birth: String = edittext_date_of_birth?.text.toString()
            val phone: String = edittext_phone_number?.text.toString()
            adapter.addItem(Client(name, birth, phone))
            adapter.notifyDataSetChanged()
            textview?.text = "${adapter.itemCount.toString()}명"
        }
    }
}
