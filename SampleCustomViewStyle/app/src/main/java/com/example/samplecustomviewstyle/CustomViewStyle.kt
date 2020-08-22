package com.example.samplecustomviewstyle

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomViewStyle(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    lateinit var paint: Paint

    init {
        paint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        canvas?.drawRect(10F, 10F, 100F, 100F, paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2.0F
        paint.color = Color.GREEN
        canvas?.drawRect(10F, 10F, 100F, 100F, paint)

        paint.style = Paint.Style.FILL
        paint.setARGB(128, 0, 0, 255)
        canvas?.drawRect(120F, 10F, 210F, 100F, paint)

        val dashEffect = DashPathEffect(FloatArray(2) {5F}, 1F)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3.0F
        paint.pathEffect = dashEffect
        paint.color = Color.GREEN
        canvas?.drawRect(120F, 10F, 210F, 100F, paint)

        paint = Paint()

        paint.color = Color.MAGENTA
        canvas?.drawCircle(50F, 160F, 40F, paint)

        paint.isAntiAlias = true
        canvas?.drawCircle(160F, 160F, 40F, paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1F
        paint.color = Color.MAGENTA
        paint.textSize = 30F
        canvas?.drawText("Text (Stroke)", 20F, 260F, paint)

        paint.style = Paint.Style.FILL
        paint.textSize = 30F
        canvas?.drawText("Text", 20F, 320F, paint)

        @Suppress("DEPRECATION")
        canvas?.clipRect(220F, 240F, 250F, 270F, Region.Op.INTERSECT)
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
        canvas?.drawRect(220F, 240F, 320F, 340F, paint)
    }
}