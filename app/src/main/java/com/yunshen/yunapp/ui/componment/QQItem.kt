package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun QQItem(){
    LazyColumn {
        item{
            Text(text = "QQ页面")
        }
    }
}