package com.example.samplecustomviewimage

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomViewImage(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    lateinit var cacheBitmap: Bitmap
    lateinit var cacheCanvas: Canvas
    lateinit var mPaint: Paint

    init {
        mPaint = Paint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        createCacheBitmap(w, h)
        testDrawing()
    }

    private fun createCacheBitmap(w: Int, h: Int) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        cacheCanvas = Canvas()
        cacheCanvas.setBitmap(cacheBitmap)
    }

    private fun testDrawing() {
        cacheCanvas.drawColor(Color.WHITE)

        mPaint.color = Color.RED
        cacheCanvas.drawRect(100F, 100F, 200F, 200F, mPaint)

        val srcImg = BitmapFactory.decodeResource(resources, R.drawable.waterdrop)
        cacheCanvas.drawBitmap(srcImg, 30F, 30F, mPaint)

        val horInverseMatrix = Matrix()
        horInverseMatrix.setScale(-1F, 1F)
        val horInverseImg = Bitmap.createBitmap(srcImg, 0, 0, srcImg.width, srcImg.height, horInverseMatrix, false)
        cacheCanvas.drawBitmap(horInverseImg, 30F, 130F, mPaint)

        val verInverseMatrix = Matrix()
        verInverseMatrix.setScale(1F, -1F)
        val verInverseImg = Bitmap.createBitmap(srcImg, 0, 0, srcImg.width, srcImg.height, verInverseMatrix, false)
        cacheCanvas.drawBitmap(verInverseImg, 30F, 230F, mPaint)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(cacheBitmap, 0F, 0F, null)
    }
}