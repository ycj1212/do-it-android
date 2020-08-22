package com.example.samplecustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class CustomView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var paint: Paint = Paint()

    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(100F, 100F, 200F, 200F, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            Toast.makeText(super.getContext(), "MotionEvent.ACTION_DOWN : ${event.x}, ${event.y}", Toast.LENGTH_LONG).show()
        }

        return super.onTouchEvent(event)
    }
}