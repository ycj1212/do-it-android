package com.example.samplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager

        val adapter: PersonAdapter = PersonAdapter()

        adapter.addItem(Person("김민수", "010-1000-1000"))
        adapter.addItem(Person("김하늘", "010-2000-2000"))
        adapter.addItem(Person("홍길동", "010-3000-3000"))

        recyclerView.adapter = adapter
    }
}
