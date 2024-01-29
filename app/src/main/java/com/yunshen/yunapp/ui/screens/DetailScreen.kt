package com.yunshen.yunapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunshen.yunapp.ui.componment.ChartView
import com.yunshen.yunapp.ui.componment.CircleZRing
import com.yunshen.yunapp.ui.componment.DailyTaskContent
import com.yunshen.yunapp.viewmodel.TaskViewModel


@Composable
fun DetailScreen(taskViewModel: TaskViewModel = viewModel()) {
    var boxWith :Int
    with(LocalConfiguration.current){
        boxWith = screenWidthDp / 2
    }

    LaunchedEffect(taskViewModel.pointOfYear){
        taskViewModel.updatePointOfYear()
        taskViewModel.updateTips()
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Brush.verticalGradient(listOf(Color(0xFF575E72), Color.White)))) {
        Row(modifier = Modifier
            .statusBarsPadding()
            .height(56.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "学习详情",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp)
        }

        //学习周期
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
            item{
                Text(text = taskViewModel.taskData,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            item {
                Box (contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWith.dp)
                        .padding(top = 8.dp)){
                    //圆环
                    CircleZRing(boxWith = boxWith, taskViewModel)
                    //数据
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                           buildAnnotatedString { append(taskViewModel.pointOfYear.toString())
                                                withStyle(SpanStyle(fontSize = 12.sp)){
                                                    append("分")
                                                }},
                            fontSize = 36.sp,
                            color = Color.White
                        )
                        Text(text = "学年积分",
                            fontSize = 12.sp,
                            color = Color.White)
                    }
                }
            }
            item{
                Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp)){
                    Column {
                        Text(text = "${taskViewModel.totalPointOfYear} 分",
                            fontSize = 16.sp,
                            color = Color.White)
                        Text(text = "学年积分",
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.White)
                    }
                    Column {
                        Text(text = "${taskViewModel.totalPointOfYear - taskViewModel.pointOfYear} 分",
                            fontSize = 16.sp,
                            color = Color.White)
                        Text(text = "还差 ",
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.White)
                    }
                }
            }
            //学习明细
            item{
                Column(modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(Color.White)
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)) {
                    Text(text = "学习明细",
                        fontSize = 16.sp,
                        color = Color(0xff333333)
                    )
                    Text(text = "最近一周获得积分情况",
                        fontSize = 14.sp,
                        color = Color(0xff999999)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    //积分情况折线图
                    ChartView(taskViewModel.pointOfWeak)

                    Row {
                        taskViewModel.week.forEach {
                            Text(text = it,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f))
                        }
                    }
                    //今日提醒
                    Text(text = taskViewModel.tips,
                        color = Color(0xff149EE7),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0x33149EE7))
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    //每日任务
                    DailyTaskContent()
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun DetailScreenPreview(){
    DetailScreen()
}