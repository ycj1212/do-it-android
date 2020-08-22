package com.example.samplecustomviewdrawable

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager

class CustomViewDrawable(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private lateinit var upperDrawable: ShapeDrawable
    private lateinit var lowerDrawable: ShapeDrawable

    init {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val width = display.width
        val height = display.height

        val curRes = resources
        val blackColor = curRes.getColor(R.color.color01)
        val grayColor = curRes.getColor(R.color.color02)
        val darkGrayColor = curRes.getColor(R.color.color03)

        upperDrawable = ShapeDrawable()

        val rectangle = RectShape()
        rectangle.resize(width.toFloat(), height.toFloat()*2/3)
        upperDrawable.shape = rectangle
        upperDrawable.setBounds(0, 0, width, height*2/3)

        val gradient = LinearGradient(0F, 0F, 0F, height.toFloat()*2/3, grayColor, blackColor, Shader.TileMode.CLAMP)

        val paint = upperDrawable.paint
        paint.shader = gradient

        lowerDrawable = ShapeDrawable()

        val rectangle2 = RectShape()
        rectangle2.resize(width.toFloat(), height.toFloat()*1/3)
        lowerDrawable.shape = rectangle2
        lowerDrawable.setBounds(0, height*2/3, width, height)

        val gradient2 = LinearGradient(0F, 0F, 0F, height.toFloat()*1/3, blackColor, darkGrayColor, Shader.TileMode.CLAMP)

        val paint2 = lowerDrawable.paint
        paint2.shader = gradient2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        upperDrawable.draw(canvas!!)
        lowerDrawable.draw(canvas)

        val pathPaint = Paint()
        pathPaint.isAntiAlias = true
        pathPaint.color = Color.YELLOW
        pathPaint.style = Paint.Style.STROKE
        pathPaint.strokeWidth = 16.0F
        pathPaint.strokeCap = Paint.Cap.BUTT
        pathPaint.strokeJoin = Paint.Join.MITER

        val path = Path()
        path.moveTo(20F, 20F)
        path.lineTo(120F, 20F)
        path.lineTo(160F, 90F)
        path.lineTo(180F, 80F)
        path.lineTo(200F, 120F)

        canvas.drawPath(path, pathPaint)

        pathPaint.color = Color.WHITE
        pathPaint.strokeCap = Paint.Cap.ROUND
        pathPaint.strokeJoin = Paint.Join.ROUND

        path.offset(30F, 120F)
        canvas.drawPath(path, pathPaint)

        pathPaint.color = Color.CYAN
        pathPaint.strokeCap = Paint.Cap.SQUARE
        pathPaint.strokeJoin = Paint.Join.BEVEL

        path.offset(30F, 120F)
        canvas.drawPath(path, pathPaint)
    }
}