package com.example.samplemultitouch

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

private const val TAG = "ImageDisplayView"
val MAX_SCALE_RATIO = 5.0F
val MIN_SCALE_RATIO = 0.1F

class ImageDisplayView(context: Context, attrs: AttributeSet? = null) : View(context, attrs), View.OnTouchListener {
    lateinit var mContext: Context
    lateinit var mCanvas: Canvas
    lateinit var mBitmap: Bitmap
    lateinit var mPaint: Paint

    var lastX = 0
    var lastY = 0

    lateinit var sourceBitmap: Bitmap
    lateinit var mMatrix: Matrix

    var sourceWidth = 0.0F
    var sourceHeight = 0.0F

    var bitmapCenterX = 0.0F
    var bitmapCenterY = 0.0F

    var scaleRatio = 0.0F
    var totalScaleRatio = 0.0F

    var displayWidth = 0.0F
    var displayHeight = 0.0F

    var displayCenterX = 0
    var displayCenterY = 0

    var startX = 0.0F
    var startY = 0.0F

    var oldDistance = 0.0F

    var oldPointerCount = 0
    var isScrolling = false
    var distanceThreshold = 3.0F

    init {
        mContext = context
        mPaint = Paint()
        mMatrix = Matrix()

        lastX = -1
        lastY = -1

        setOnTouchListener(this)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (w > 0 && h > 0) {
            newImage(w, h)
            redraw()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(mBitmap, 0f, 0f, null)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val action = event!!.action
        val pointerCount = event.pointerCount

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                if (pointerCount == 1) {
                    val curX = event.x
                    val curY = event.y

                    startX = curX
                    startY = curY
                } else if (pointerCount == 2) {
                    oldDistance = 0.0F
                    isScrolling = true
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (pointerCount == 1) {
                    if (isScrolling) {
                        return true
                    }

                    val curX = event.x
                    val curY = event.y

                    if (startX == 0.0F) {
                        startX = curX
                        startY = curY

                        return true
                    }

                    val offsetX = startX - curX
                    val offsetY = startY - curY

                    if (oldPointerCount != 2) {
                        if (totalScaleRatio > 1.0F) {
                            moveImage(-offsetX, -offsetY)
                        }

                        startX = curX
                        startY = curY
                    }
                } else if (pointerCount == 2) {

                    scaleImage(outScaleRatio)

                }

                oldPointerCount = pointerCount
            }
            MotionEvent.ACTION_UP -> {
                if (pointerCount == 1) {
                    val curX = event.x
                    val curY = event.y

                    val offsetX = startX - curX
                    val offsetY = startY - curY

                    if (oldPointerCount != 2) {
                        moveImage(-offsetX, -offsetY)
                    }
                } else {
                    isScrolling = false
                }
                return true
            }
        }
        return true
    }

    private fun scaleImage(inScaleRatio: Float) {
        mMatrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY)
        mMatrix.postRotate(0f)

        totalScaleRatio *= inScaleRatio

        redraw()
    }

    private fun moveImage(offsetX: Float, offsetY: Float) {
        mMatrix.postTranslate(offsetX, offsetY)

        redraw()
    }
}