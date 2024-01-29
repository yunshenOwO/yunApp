package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ChartView(points:List<Float>){
    //小圆圈半径
    val circleRadius = 2.5
    val perY = 8
    //每一个行的高度
    val heightForRow = 24
    val countForRow = 5
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 * 2
    val canvasHeight = heightForRow * countForRow + circleRadius * 2
    val averageOfWidth = canvasWidth/7

    Canvas(modifier = Modifier.size(width = canvasWidth.dp,
        height = canvasHeight.dp)){
        for (index in 0 .. countForRow){
            //行高乘index
            val y = (heightForRow * index + circleRadius).dp.toPx()
            drawLine(Color(0xffEEEEEE),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 2.5f
            )
        }

        //画背景图片
        for (index in 0 until points.count()){
            val circleCenter = Offset((averageOfWidth * index + averageOfWidth/2).dp.toPx(),
                (heightForRow * countForRow - points[index] * perY).dp.toPx())
            drawCircle(Color(0xff149EE7),
                radius = circleRadius.dp.toPx(),
                center = circleCenter,
                style = Stroke(width = 5f))

            if (index < points.count() - 1){
                val nextPointOffset = Offset((averageOfWidth * (index + 1) + averageOfWidth/2).dp.toPx(),
                    (heightForRow * countForRow - points[index + 1] * perY).dp.toPx())
                drawLine(Color(0xff149EE7),
                    start = circleCenter,
                    end = nextPointOffset,
                    strokeWidth = 5f)
            }
        }
    }
}