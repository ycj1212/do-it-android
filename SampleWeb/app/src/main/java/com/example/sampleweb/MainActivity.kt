package com.example.sampleweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null
    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        webView = findViewById(R.id.webView)

        val webSettings: WebSettings = webView!!.settings
        webSettings.javaScriptEnabled = true

        webView?.webViewClient = ViewClient()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            webView?.loadUrl(editText?.text.toString())
        }
    }

    private class ViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)

            return true
        }
    }
}
