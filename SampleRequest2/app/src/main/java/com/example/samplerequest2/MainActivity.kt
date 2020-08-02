package com.example.samplerequest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

lateinit var requestQueue: RequestQueue

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            makeRequest()
        }

        requestQueue = Volley.newRequestQueue(applicationContext)
    }

    private fun makeRequest() {
        val url = editText.text.toString()
        val request = object : StringRequest(Request.Method.GET, url,
                object : Response.Listener<String> {
                    override fun onResponse(response: String?) {
                        println("응답-> $response")
                        processResponse(response!!)
                    }

                    fun processResponse(response: String) {
                        val gson = Gson()
                        val movieList = gson.fromJson(response, MovieList::class.java)
                        println("영화 정보의 수: ${movieList.boxOfficeResult.dailyBoxOfficeList.size}")
                    }
                },
                Response.ErrorListener { error -> println("에러-> ${error?.message}") } ) {
            override fun getParams(): MutableMap<String, String> {
                return HashMap()
            }
        }
        request.setShouldCache(false)
        requestQueue.add(request)
        println("요청 보냄.")
    }

    private fun println(data: String) {
        textView.append("$data\n")
    }
}

