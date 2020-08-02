package com.example.samplemovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter
    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
        recyclerView = findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter = MovieAdapter()
        recyclerView.adapter = adapter

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            makeRequest()
        }

        requestQueue = Volley.newRequestQueue(applicationContext)
    }

    private fun makeRequest() {
        val url = editText.text.toString()
        val request = object : StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                println("응답-> $response")

                processResponse(response)
            },
            Response.ErrorListener { error ->
                println("에러-> ${error.message}")
            }
        ) {
            override fun getParams(): MutableMap<String, String> = HashMap()
        }
        request.setShouldCache(false)
        requestQueue.add(request)
        println("요청 보냄")
    }

    private fun println(data: String) {
        Log.d("MainActivity", data)
    }

    private fun processResponse(response: String) {
        val gson = Gson()
        val movieList = gson.fromJson(response, MovieList::class.java)

        println("영화 정보 수: ${movieList.boxOfficeResult.dailyBoxOfficeList.size}")

        for (i in 0 until movieList.boxOfficeResult.dailyBoxOfficeList.size) {
            val movie = movieList.boxOfficeResult.dailyBoxOfficeList[i]
            adapter.addItem(movie)
        }

        adapter.notifyDataSetChanged()
    }
}
