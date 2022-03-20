package com.yologger.project

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView: View {

    private val paint = Paint()

    private var color: Int = Color.TRANSPARENT
    private var radius: Float = 0F

    private var centerX: Float = 0.0f
        get() {
            return radius
        }

    private var centerY: Float = 0.0f
        get() {
            return radius
        }

    constructor(context: Context): super(context) {

    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.FigureView)
        color = typedArray.getColor(R.styleable.FigureView_figureColor, Color.TRANSPARENT)
        radius = typedArray.getDimension(R.styleable.FigureView_radius, 0.0f)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension((centerX*2).toInt(), (centerY*2).toInt());
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = color
        canvas?.drawColor(Color.GRAY)
        canvas?.drawCircle(centerX, centerX, radius, paint)
    }
}