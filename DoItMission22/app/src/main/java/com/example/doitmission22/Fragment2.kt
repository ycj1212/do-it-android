package com.example.doitmission22

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment2() : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private lateinit var callback: OnDatabaseCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = activity as OnDatabaseCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment2, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter = BookAdapter()
        recyclerView.adapter = adapter

        val result = callback.selectAll()
        adapter.items = result
        adapter.notifyDataSetChanged()

        return view
    }
}