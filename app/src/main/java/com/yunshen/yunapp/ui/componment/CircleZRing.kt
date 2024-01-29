package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.yunshen.yunapp.viewmodel.TaskViewModel

@Composable
fun CircleZRing(boxWith:Int, vm:TaskViewModel) {
    val strokeWidth = 30f
    Canvas(modifier = Modifier.size(boxWith.dp)){
        drawArc(Color(0,0,0,15),
            startAngle = 160f,
            sweepAngle = 220f,
            useCenter = false, //底边是否链接
            style = Stroke(strokeWidth, cap = StrokeCap.Round))

        drawArc(Color.White,
            startAngle = 160f,
            sweepAngle = vm.pointOfyearPercent,
            useCenter = false, //底边是否链接
            style = Stroke(strokeWidth, cap = StrokeCap.Round))
    }
}