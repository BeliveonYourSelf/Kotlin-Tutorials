package com.steps.tracker.machine.analyzer.widgets
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec.AT_MOST
import android.view.View.MeasureSpec.EXACTLY
import com.steps.tracker.machine.analyzer.R
import java.lang.Integer.min


@SuppressLint("ResourceType")
class CalorieChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var backgroundPaint: Paint = Paint()
    private var rectangle: RectF? = null
    private var margin: Float
    private var arcProportionValue: Float = 0f
    private var typedArray: TypedArray

    private data class GradientColor(var startColor: Int, var endColor: Int)

    private var lisGradientColor = arrayListOf(
        GradientColor(Color.parseColor("#40AEF812"), Color.parseColor("#AEF812")),
        GradientColor(Color.parseColor("#40FECA10"), Color.parseColor("#FECA10")),
        GradientColor(Color.parseColor("#40FF5E01"), Color.parseColor("#FF5E01")),
        GradientColor(Color.parseColor("#40FF0101"), Color.parseColor("#FF0000"))
    )

    private var strokeColor = Color.parseColor("#E3E9ED")
    private var strokewidth = 30f

    init {
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalorieChart)

        strokeColor = typedArray.getColor(R.styleable.CalorieChart_strokeColor, Color.parseColor("#E3E9ED"))
        strokewidth = typedArray.getDimension(R.styleable.CalorieChart_strokeWith, 50f).toFloat()

        backgroundPaint = Paint().apply {
            isAntiAlias = true
            color = strokeColor
            strokeCap = Paint.Cap.ROUND
            strokeWidth = strokewidth
            style = Paint.Style.STROKE
        }
        margin = 6f
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val desiredWidth = widthSize // Use the full available width
        val desiredHeight = (desiredWidth * 0.75f).toInt() // Set the height to 3/4 of the width

        val finalWidth: Int = when (widthMode) {
            EXACTLY -> widthSize
            AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth // UNSPECIFIED
        }

        val finalHeight: Int = when (heightMode) {
            EXACTLY -> heightSize
            AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(finalWidth, finalHeight)
    }

    val arcProportionBg=0.6f
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (rectangle == null) {
            val centerX = width / 2f
            val centerY = width / 2f
            val rectSize = width - strokewidth
            val rectLeft = centerX - rectSize / 2
            val rectTop = centerY - rectSize / 2
            val rectRight = centerX + rectSize / 2
            val rectBottom = centerY + rectSize / 2

            rectangle = RectF(rectLeft, rectTop, rectRight, rectBottom)
        }
        val totalSweepAngle = 360f * arcProportionBg // góc bắt đầu
        val startAngle = 180 - (totalSweepAngle -180)/2 // góc của vòng cung

        drawBackground(canvas, totalSweepAngle, startAngle)
        val paintColor = Paint().apply {
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            strokeWidth = strokewidth
            style = Paint.Style.STROKE
            val startColor = lisGradientColor[position].startColor
            val endColor = lisGradientColor[position].endColor
            shader = LinearGradient(
                0f,
                0f,
                width.toFloat(),
                0f,
                startColor,
                endColor,
                Shader.TileMode.MIRROR
            )

        }
        val totalSweepAnglePaint = 360f * arcProportionValue
        val startAnglePaint = 180 - (totalSweepAngle -180)/2
        drawPaintValue(canvas, totalSweepAnglePaint, startAnglePaint, paintColor)
    }
    private fun drawPaintValue(
        canvas: Canvas,
        totalSweepAngle: Float,
        startAngle: Float,
        paintColor: Paint
    ) {

        canvas.drawArc(rectangle!!, startAngle, totalSweepAngle, false, paintColor)
    }

    private fun drawBackground(canvas: Canvas, totalSweepAngle: Float, startAngle: Float) {
        val testPaint = Paint().apply {
            isAntiAlias = true
            color = Color.GREEN
            strokeCap = Paint.Cap.ROUND
            strokeWidth = strokewidth
            style = Paint.Style.STROKE
        }
        // tọa độ tính từ tâm trục bên phải
        canvas.drawArc(rectangle!!, startAngle, totalSweepAngle, false, backgroundPaint)
    }

    private var position = 0

    fun setArcValue(maxValue: Float, value: Float) {
        var ratioValue = value / maxValue
        if (ratioValue > 1) {
            ratioValue = 1f
        }
        ratioValue *= 0.6f

        if (ratioValue <= 0.15) {
            position = 0
        } else if ( ratioValue <= 0.3f) {
            position = 1
        } else if ( ratioValue <= 0.45) {
            position = 2
        } else  {
            position = 3
        }
        this.arcProportionValue = ratioValue
        invalidate()
    }
}