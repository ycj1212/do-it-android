package com.example.samplerecyclerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager: GridLayoutManager = GridLayoutManager(this, 2)

        recyclerView.layoutManager = layoutManager

        val adapter: PersonAdapter = PersonAdapter()

        adapter.addItem(Person("김민수", "010-1000-1000"))
        adapter.addItem(Person("김하늘", "010-2000-2000"))
        adapter.addItem(Person("홍길동", "010-3000-3000"))
        adapter.addItem(Person("내이름1", "010-4000-4000"))
        adapter.addItem(Person("내이름2", "010-4000-4000"))
        adapter.addItem(Person("내이름3", "010-4000-4000"))
        adapter.addItem(Person("내이름4", "010-4000-4000"))
        adapter.addItem(Person("내이름5", "010-4000-4000"))
        adapter.addItem(Person("내이름6", "010-4000-4000"))
        adapter.addItem(Person("내이름7", "010-4000-4000"))
        adapter.addItem(Person("내이름8", "010-4000-4000"))
        adapter.addItem(Person("내이름9", "010-4000-4000"))
        adapter.addItem(Person("내이름10", "010-4000-4000"))

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: OnPersonItemClickListener {
            override fun onItemClick(holder: PersonAdapter.ViewHolder, view: View, position: Int) {
                val item = adapter.getItem(position)
                Toast.makeText(applicationContext, "아이템 선택됨: ${item.name}", Toast.LENGTH_LONG).show()
            }

        })
    }
}
