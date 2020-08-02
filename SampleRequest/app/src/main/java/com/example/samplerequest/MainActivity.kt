package com.example.samplerequest

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
            Response.Listener<String> { response -> println("응답-> $response") },
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
