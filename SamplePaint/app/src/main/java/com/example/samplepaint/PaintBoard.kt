package com.example.samplepaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintBoard(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    lateinit var mCanvas: Canvas
    lateinit var mBitmap: Bitmap
    lateinit var mPaint: Paint

    var lastX: Int = 0
    var lastY: Int = 0

    init {
        mPaint = Paint()
        mPaint.color = Color.BLACK

        lastX = -1
        lastY = -1
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas()
        canvas.setBitmap(img)
        canvas.drawColor(Color.WHITE)

        mBitmap = img
        mCanvas = canvas
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(mBitmap, 0F, 0F, null)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event!!.action
        val X = event.x.toInt()
        val Y = event.y.toInt()

        when (action) {
            MotionEvent.ACTION_UP -> {
                lastX = -1
                lastY = -1
            }
            MotionEvent.ACTION_DOWN -> {
                if (lastX != -1) {
                    if (X != lastX || Y != lastY) {
                        mCanvas.drawLine(lastX.toFloat(), lastY.toFloat(), X.toFloat(), Y.toFloat(), mPaint)
                    }
                }

                lastX = X
                lastY = Y
            }
            MotionEvent.ACTION_MOVE -> {
                if (lastX != -1) {
                    mCanvas.drawLine(lastX.toFloat(), lastY.toFloat(), X.toFloat(), Y.toFloat(), mPaint)
                }

                lastX = X
                lastY = Y
            }
        }

        invalidate()

        return true
    }
}