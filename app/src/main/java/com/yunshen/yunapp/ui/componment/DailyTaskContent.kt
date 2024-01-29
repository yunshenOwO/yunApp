package com.yunshen.yunapp.ui.componment

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyTaskContent() {
    DailyTaskItem(
        title = "登录任务",
        secondDailyTitle = "已获得100积分",
        doc = "已获得100积分/每日上限100", 0.2f)

    DailyTaskItem(
        title = "其他任务",
        secondDailyTitle = "已获得100积分",
        doc = "已获得100积分/每日上限100", 1f)
}

@Composable
fun DailyTaskItem(title:String, secondDailyTitle:String, doc:String, progress:Float) {
    val inlineContentId = "inlineContentId"
    val secondDailyTitleBuild = buildAnnotatedString {
        append(secondDailyTitle)
        appendInlineContent(inlineContentId, "[icon]")
    }

    val inlineContent = mapOf(
        Pair(inlineContentId, InlineTextContent(Placeholder(width = 14.sp, height = 14.sp, placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline)){
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.clickable {
                Log.i("Task", "DailyTaskItem: 点击了图标")
            })
        })
    )
    Row (modifier = Modifier.fillMaxWidth()
        .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Column(modifier = Modifier.weight(7.5f)) {
            Text(text = title, fontSize = 16.sp,
                color = Color(0xff333333)
            )
            Text(text = secondDailyTitleBuild,
                inlineContent = inlineContent,
                fontSize = 14.sp, color = Color(0xff333333))

            Row (verticalAlignment = Alignment.CenterVertically){
                LinearProgressIndicator(progress = progress, modifier = Modifier.weight(3f))
                Text(text = doc, fontSize = 10.sp, color = Color(0xff333333),
                    modifier = Modifier.weight(7f, fill = false).padding(8.dp))
            }
        }
        OutlinedButton(onClick = {  },
            border = if (progress >= 1f) ButtonDefaults.outlinedButtonBorder else BorderStroke(1.dp, Color(0xffff5900)),
            shape = CircleShape,
            enabled = (progress < 1f),
            modifier = Modifier.weight(2.5f),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xffff5900))) {
            Text(text = if (progress >= 1f) "已完成" else "去学习")
        }
    }
}