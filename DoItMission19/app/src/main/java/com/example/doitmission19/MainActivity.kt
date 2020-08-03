package com.example.doitmission19

import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var data: TextView
    lateinit var webView: WebView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        data = findViewById(R.id.data)
        webView = findViewById(R.id.webView)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = ViewClient()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val urlStr = editText.text.toString()
            Thread(Runnable {
                request(urlStr)
            }).start()
        }
    }

    class ViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
    }

    private fun request(urlStr: String) {
        val output = StringBuilder()
        val url = URL(urlStr)
        val conn = url.openConnection() as HttpURLConnection

        conn.connectTimeout = 10000
        conn.requestMethod = "GET"
        conn.doInput = true

        val resCode = conn.responseCode
        val reader = BufferedReader(InputStreamReader(conn.inputStream))
        var line: String? = null
        while (true) {
            line = reader.readLine()
            if (line == null) {
                break
            }
            output.append("$line\n")
        }
        reader.close()
        conn.disconnect()

        println("응답-> ${output.toString()}")
    }

    private fun println(str: String) {
        handler.post {
            data.append(str)
            val encodedHtml: String = Base64.encodeToString(str.toByteArray(), Base64.NO_PADDING)
            webView.loadData(encodedHtml, "text/html", "base64")
        }
    }
}
