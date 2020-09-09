package com.example.samplepushsend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

lateinit var requestQueue: RequestQueue
lateinit var regId: String

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
            val input = editText.text.toString()
            send(input)
        }

        requestQueue = Volley.newRequestQueue(applicationContext)
    }

    fun send(input: String) {
        val requestData = JSONObject()

        requestData.put("priority", "high")

        val dataObj = JSONObject()
        dataObj.put("contents", input)
        requestData.put("data", dataObj)

        val idArray = JSONArray()
        idArray.put(0, regId)
        requestData.put("registration_ids", idArray)

        sendData(requestData, object : SendResponseListener {
            override fun onRequestStarted() {
                println("onRequestStarted() 호출됨.")
            }

            override fun onRequestCompleted() {
                println("onRequestCompleted() 호출됨.")
            }

            override fun onRequestWithError(error: VolleyError) {
                println("onRequestWithError() 호출됨.")
            }
        })
    }

    interface SendResponseListener {
        fun onRequestStarted()
        fun onRequestCompleted()
        fun onRequestWithError(error: VolleyError)
    }

    fun sendData(requestData: JSONObject, listener: SendResponseListener) {
        val request = object : JsonObjectRequest(
            Request.Method.POST,
            "https://fcm.googleapis.com/fcm/send",
            requestData,
            Response.Listener<JSONObject> {
                listener.onRequestCompleted()
            }, Response.ErrorListener {
                listener.onRequestWithError(it)
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return HashMap()
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "key=AAAAn1oU4ZA:APA91bG1vJhykKn-WHo6vDebVZEzX0CbC0UAwBD9KNm0b6KGvd9LPwCTnGPowleQWP1PeqkrFoR6H6wkUBQqG6dMPzw_0Kulkvg0Ops7RGkLzOwWe8RxSj8d_qB4skhwywAaCI4xywdq"

                return headers
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
    }

    fun println(data: String) {
        textView.append("$data\n")
    }
}
