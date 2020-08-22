package com.example.samplepaint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

const val TOUCH_TOLERANCE = 8

class BestPaintBoard(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    var changed = false

    lateinit var mCanvas: Canvas
    lateinit var mBitmap: Bitmap
    lateinit var mPaint: Paint

    var lastX: Float = 0F
    var lastY: Float = 0F

    val mPath = Path()

    var mCurveEndX: Float = 0F
    var mCurveEndY: Float = 0F

    var mInvalidateExtraBorder = 10

    init {
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 3.0F

        lastX = -1F
        lastY = -1F
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
        when (event!!.action) {
            MotionEvent.ACTION_UP -> {
                changed = true

                val rect = touchUp(event, false)
                invalidate(rect)

                mPath.rewind()

                return true
            }
            MotionEvent.ACTION_DOWN -> {
                val rect = touchDown(event)
                invalidate(rect)

                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val rect = touchMove(event)
                invalidate(rect)

                return true
            }
        }

        return false
    }

    private fun touchDown(event: MotionEvent): Rect {
        val x = event.x
        val y = event.y

        lastX = x
        lastY = y

        val mInvalidRect = Rect()
        mPath.moveTo(x, y)

        val border = mInvalidateExtraBorder
        mInvalidRect.set(x.toInt()-border, y.toInt()-border, x.toInt()+border, y.toInt()+border)
        mCurveEndX = x
        mCurveEndY = y

        mCanvas.drawPath(mPath, mPaint)

        return mInvalidRect
    }

    private fun touchMove(event: MotionEvent): Rect {
        return processMove(event)
    }

    private fun touchUp(event: MotionEvent, cancel: Boolean): Rect {
        return processMove(event)
    }

    private fun processMove(event: MotionEvent): Rect {
        val x = event.x
        val y = event.y

        val dx = abs(x - lastX)
        val dy = abs(y - lastY)

        val mInvalidRect = Rect()
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            val border = mInvalidateExtraBorder
            mInvalidRect.set(mCurveEndX.toInt()-border, mCurveEndY.toInt()-border, mCurveEndX.toInt()+border, mCurveEndY.toInt()+border)

            mCurveEndX = (x + lastX) / 2F
            mCurveEndY = (y + lastY) / 2F
            val cX = mCurveEndX
            val cY = mCurveEndY

            mPath.quadTo(lastX, lastY, cX, cY)

            mInvalidRect.union(lastX.toInt()-border, lastY.toInt()-border, lastX.toInt()+border, lastY.toInt()+border)
            mInvalidRect.union(cX.toInt()-border, cY.toInt()-border, cX.toInt()+border, cY.toInt()+border)

            lastX = x
            lastY = y

            mCanvas.drawPath(mPath, mPaint)
        }

        return mInvalidRect
    }
}