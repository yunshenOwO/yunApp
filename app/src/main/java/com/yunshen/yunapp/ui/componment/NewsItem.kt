package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yunshen.yunapp.viewmodel.ArticleViewModel
import com.yunshen.yunapp.viewmodel.MainViewModel
import com.yunshen.yunapp.viewmodel.VideoModel


@Composable
fun NewsItem(vm:MainViewModel,
                articleViewModel: ArticleViewModel,
                videoModel: VideoModel,
                onNavigateToArticle: () -> Unit,
                onNavigateToVideo: () -> Unit){
    TabRow(selectedTabIndex = vm.currentypeIndex,
        indicator = {},//取消分割线，和下划线
        divider = {}) {
        vm.types.forEachIndexed { index, item ->
            LeadingIconTab(selected = vm.currentypeIndex == index,
                onClick = {
                    vm.updateTypeIndex(index)
                }, icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                }, text = {
                    Text(text = item.title,
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp)
                })
        }
    }

    LazyColumn {
        //轮播图
        item { SwiperContent(vm) }
        //通知
        item { Notification(vm) }

        //文章页面
        if (vm.currentypeIndex == 0){
            items(articleViewModel.articleList){
                    article ->
                ArticleItem(articleEntity = article, Modifier.clickable {
                    onNavigateToArticle()
                })
            }
        }else{
            //视频页面
            items(videoModel.list){
                    videoItem ->
                VideoItem(videoItem = videoItem, modifier = Modifier.clickable {
                    onNavigateToVideo()
                })
            }
        }
    }
}