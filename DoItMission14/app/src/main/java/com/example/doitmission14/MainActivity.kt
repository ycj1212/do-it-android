package com.example.doitmission14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val layoutManager: GridLayoutManager = GridLayoutManager(this, 2)
        val adapter: ProductAdapter = ProductAdapter()

        recyclerView.layoutManager = layoutManager

        adapter.addItem(Product("아메리카노", "3,000", "시그니처 메뉴"))
        adapter.addItem(Product("카페라떼", "3,500", "시그니처 메뉴"))
        adapter.addItem(Product("카푸치노", "4,000", "시그니처 메뉴"))
        adapter.addItem(Product("카라멜마끼아또", "4,000", "시그니처 메뉴"))
        adapter.addItem(Product("다쿠아즈", "3,500", "시그니처 메뉴"))
        adapter.addItem(Product("팥빙수", "6,000", "시그니처 메뉴"))

        recyclerView.adapter = adapter
    }
}
