package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yunshen.yunapp.model.entity.ArticleEntity

@Composable
fun ArticleItem(articleEntity: ArticleEntity, modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = articleEntity.title,
            color = Color(0xff333333),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "来源: ${articleEntity.source}",
                color = Color(0xff999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            Text(text = "时间: ${articleEntity.time}",
                color = Color(0xff999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider() //分割线
    }
}