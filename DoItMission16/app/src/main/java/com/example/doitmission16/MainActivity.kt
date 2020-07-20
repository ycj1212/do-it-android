package com.example.doitmission16

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class MainActivity : AppCompatActivity(), Animation.AnimationListener {
    var isOpenButtonClicked: Boolean = false
    var moveButton: Button? = null
    var openButton: Button? = null
    var layout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editText)

        moveButton = findViewById(R.id.moveButton)
        openButton = findViewById(R.id.openButton)

        layout = findViewById(R.id.layout)

        val webView: WebView = findViewById(R.id.webView)

        val translateUp: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_up)
        val translateDown: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_down)

        translateUp.setAnimationListener(this)
        translateDown.setAnimationListener(this)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = ViewClient()

        openButton?.visibility = View.INVISIBLE

        moveButton?.setOnClickListener {
            val site = editText.text.toString()
            webView.loadUrl(site)
            val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            isOpenButtonClicked = false
            layout?.startAnimation(translateUp)
        }

        openButton?.setOnClickListener {
            isOpenButtonClicked = true
            layout?.startAnimation(translateDown)
        }
    }

    override fun onAnimationStart(animation: Animation?) {
        if (isOpenButtonClicked) {
            openButton?.visibility = View.INVISIBLE
            layout?.visibility = View.VISIBLE
        }
    }

    override fun onAnimationEnd(animation: Animation?) {
        if (!isOpenButtonClicked) {
            openButton?.visibility = View.VISIBLE
            layout?.visibility = View.INVISIBLE
        }
    }

    override fun onAnimationRepeat(animation: Animation?) {}

    class ViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)

            return true
        }
    }
}
