package com.yunshen.yunapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BilBilVideoScreen(onBack:()-> Unit){
    Column (modifier = Modifier.clickable {
        onBack()
    }){
        Text(text = "BIUlBIL详情页")
    }
}