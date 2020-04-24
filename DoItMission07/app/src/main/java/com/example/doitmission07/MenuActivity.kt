package com.example.doitmission07

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val button_client_management = button_client_management
        button_client_management.setOnClickListener(this)
        val button_sales_management = button_sales_management
        button_sales_management.setOnClickListener(this)
        val button_product_management = button_product_management
        button_product_management.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = Intent()
        var s = ""
        when (view.id) {
            R.id.button_client_management -> s = "고객 관리"
            R.id.button_sales_management -> s = "매출 관리"
            R.id.button_product_management -> s = "상품 관리"
        }
        intent.putExtra("name", s)
        setResult(RESULT_OK, intent)
        finish()
    }
}