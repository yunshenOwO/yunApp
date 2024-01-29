package com.yunshen.yunapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunshen.yunapp.ui.componment.WebViewItem
import com.yunshen.yunapp.ui.componment.rememberWebViewState
import com.yunshen.yunapp.viewmodel.ArticleViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(arTest : ArticleViewModel = viewModel(),onBack: () -> Unit){
    val rememberWebViewState = rememberWebViewState(data = arTest.content)
    var fontSize by remember {
        mutableFloatStateOf(1.0f)
    }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutine = rememberCoroutineScope()
    BottomSheetScaffold(scaffoldState = scaffoldState,topBar = {
        TopAppBar(title = {
            Text(text = "文章详情页", fontSize = 16.sp, color = Color.Black, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }, navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                modifier = Modifier.clickable {
                    onBack()
                }) },
            actions = {
                Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.clickable {
                    coroutine.launch {
                        if (scaffoldState.bottomSheetState.isVisible){
                            scaffoldState.bottomSheetState.expand()
                        }else{
                            scaffoldState.bottomSheetState.hide()
                        }
                    }
                })
            }
        ) }, sheetContent = {
            Column (modifier = Modifier.padding(8.dp)){
                Text(text = "字体大小", fontSize=16.sp)
                Slider(value = fontSize, onValueChange = {
                    fontSize = it
                    coroutine.launch {
                        rememberWebViewState.evaluateJavascript("document.body.style.zoom = $it")
                    }
                }, steps = 3, valueRange = 0.75f..1.75f)
                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()){
                    Text("较小", fontSize=14.sp, color = Color(0xff999999))
                    Text("标准", fontSize = 14.sp, color = Color(0xff999999))
                    Text("较大", fontSize=14.sp, color = Color(0xff999999))
                    Text("超大", fontSize = 14.sp, color = Color(0xff999999))
                    Text("最大", fontSize = 14.sp, color = Color(0xff999999))
                }
    }}, sheetPeekHeight = 0.dp) {
        Box(modifier = Modifier.padding(it)){
            WebViewItem(rememberWebViewState)
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun Preview() {
    ArticleDetailScreen {

    }
}