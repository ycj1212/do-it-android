package com.example.sampletoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null
    var editText2: EditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        editText2 = findViewById(R.id.editText2)
    }

    private fun onButton1Clicked(v: View) {
        try {
            val toastView: Toast = Toast.makeText(this, "위치가 바뀐 토스트 메시지입니다.", Toast.LENGTH_LONG)
            val xOffset: Int = Integer.parseInt(editText?.getText().toString())
            val yOffset: Int = Integer.parseInt(editText2?.getText().toString())

            toastView.setGravity(Gravity.TOP, xOffset, yOffset)
            toastView.show()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    private fun onButton2Clicked(v: View) {
        val inflater: LayoutInflater = layoutInflater

        val layout: View = inflater.inflate(
            R.layout.toast_border,
            findViewById(R.id.toast_layout_root)
        )

        val text: TextView = layout.findViewById(R.id.text)

        val toast: Toast = Toast(this)
        text.text = "모양 바꾼 토스트"
        toast.setGravity(Gravity.CENTER, 0, -100)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }

    private fun onButton3Clicked(v: View) {
        Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show()
    }
}
