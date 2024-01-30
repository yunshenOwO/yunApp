package com.yunshen.yunapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunshen.module.webview.WebViewItem
import com.yunshen.module.webview.rememberWebViewState
import com.yunshen.yunapp.viewmodel.VideoModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoDetailScreen(video: VideoModel = viewModel(),onBack:() -> Unit) {
    val viewState = rememberWebViewState(data = video.videoDesc)
    Scaffold(topBar = {
        TopAppBar(
            title = {
            Text(text = "视频详情", fontSize = 16.sp, color = Color.Black,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }, navigationIcon = {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
                modifier = Modifier.clickable {
                    onBack()
                }) }, modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .statusBarsPadding())
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            //视频
            Box(modifier = Modifier.height(200.dp))
//            //标题
//            Text(text = video.videoTitle, color = Color(0xff333333), fontSize = 16.sp)
            //简介
            WebViewItem(state = viewState)

        }
    }
}


@Preview(
    showSystemUi = true
)
@Composable
fun VideoP() {
    VideoDetailScreen {

    }
}