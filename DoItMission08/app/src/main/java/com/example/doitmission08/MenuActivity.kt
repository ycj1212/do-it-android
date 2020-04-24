package com.example.doitmission08

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        println("onCreate 호출됨")
        
        val button_client = button_client
        button_client.setOnClickListener(this)
        val button_sales = button_sales
        button_sales.setOnClickListener(this)
        val button_product = button_product
        button_product.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        println("onStart 호출됨")
    }

    override fun onResume() {
        super.onResume()
        println("onResume 호출됨")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart 호출됨")
    }

    override fun onPause() {
        super.onPause()
        println("onPause 호출됨")
    }

    override fun onStop() {
        super.onStop()
        println("onStop 호출됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy 호출됨")
    }

    private fun println(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
        Log.d("Menu", s)
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v.id) {
            R.id.button_client -> intent = Intent(this, ClientActivity::class.java)
            R.id.button_sales -> intent = Intent(this, SalesActivity::class.java)
            R.id.button_product -> intent = Intent(this, ProductActivity::class.java)
        }
        startActivityForResult(intent, 102)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 102) {

            if (resultCode == 1) {
                Toast.makeText(this, data?.getStringExtra("name"), Toast.LENGTH_LONG).show()
            }
            else if (resultCode == 2) {
                setResult(0)
                finish()
            }
        }
    }
}