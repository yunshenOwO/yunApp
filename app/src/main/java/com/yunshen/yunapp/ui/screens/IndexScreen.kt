package com.yunshen.yunapp.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunshen.yunapp.ui.componment.BilBilItem
import com.yunshen.yunapp.ui.componment.NewsItem
import com.yunshen.yunapp.ui.componment.QQItem
import com.yunshen.yunapp.ui.componment.TopAppBar
import com.yunshen.yunapp.viewmodel.ArticleViewModel
import com.yunshen.yunapp.viewmodel.MainViewModel
import com.yunshen.yunapp.viewmodel.VideoModel


@Composable
fun IndexScreen(vm:MainViewModel = viewModel(),
                articleViewModel: ArticleViewModel = viewModel(),
                onNavigateToArticle: () -> Unit = {},
                onNavigateToVideo: () -> Unit = {},
                onNavigateToStudy: () -> Unit,
                goToVideo:() -> Unit,
                videoModel: VideoModel = viewModel()) {
    Column(
        modifier = Modifier
    ) {
        TopAppBar {
            Spacer(modifier = Modifier.weight(0.06f))
            //搜索按钮
            Surface(modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .weight(1f), color = Color(0x33FFFFFF)) {
                Row (modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable { Log.i("search", "IndexScreen: 点击了搜索") },
                    horizontalArrangement = Arrangement.Center){
                    Icon(imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp))
                    Text(text = "搜索", color = Color.White, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.weight(0.08f))
            //进度
            Column {
                Text(text = "学习", fontSize = 10.sp, color = Color.White)
                Text(text = "进度", fontSize = 10.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(0.04f))
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.weight(0.08f))
            Icon(imageVector = Icons.Default.Notifications, contentDescription = null, tint = Color.White, modifier = Modifier.clickable {
                onNavigateToStudy()
            })
            Spacer(modifier = Modifier.weight(0.08f))
        }
        //分类标签
        TabRow(selectedTabIndex = vm.categoryindex,
            contentColor = Color(0xFFF8F8F8),
            containerColor = Color(0xFF4A5E8F)
        ) {
            vm.category.forEachIndexed { index, category ->
                Tab(selected = vm.categoryindex == index,
                    onClick = {
                    vm.updateCatgoryIndex(index)
                }) {
                    Text(text = category.title, modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
        when(vm.categoryindex){
            0 -> {
                NewsItem(vm, articleViewModel, videoModel, onNavigateToArticle, onNavigateToVideo)
            }
            1 -> {
                BilBilItem(videoModel = videoModel, goToVideo = goToVideo)
            }
            2 -> {
                QQItem()
            }
        }
    }
}
