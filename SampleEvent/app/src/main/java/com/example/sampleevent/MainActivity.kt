package com.example.sampleevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    var view: View? = null
    var view2: View? = null
    var detector: GestureDetector? = null

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        view = findViewById(R.id.view)
        view?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val action = event?.action
                val curX = event?.x
                val curY = event?.y

                when (action) {
                    MotionEvent.ACTION_DOWN -> textView?.append("손가락 눌림 : $curX, $curY\n")
                    MotionEvent.ACTION_MOVE -> textView?.append("손가락 움직임 : $curX, $curY\n")
                    MotionEvent.ACTION_UP -> textView?.append("손가락 뗌 : $curX, $curY\n")
                }

                return true
            }
        })

        detector = GestureDetector(this, object : GestureDetector.OnGestureListener {
            override fun onDown(e: MotionEvent?): Boolean {
                textView?.append("onDown() 호출됨.")

                return true
            }

            override fun onShowPress(e: MotionEvent?) {
                textView?.append("onShowPress() 호출됨.")
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                textView?.append("onSingleTapUp() 호출됨.")

                return true
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                textView?.append("onScroll() 호출됨 : $distanceX, $distanceY")

                return true
            }

            override fun onLongPress(e: MotionEvent?) {
                textView?.append("onLongPress() 호출됨.")
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                textView?.append("onFling() 호출됨 : $velocityX, $velocityY")

                return true
            }
        })

        view2 = findViewById(R.id.view2)
        view2?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                detector?.onTouchEvent(event)
                return true
            }
        })
    }
}
