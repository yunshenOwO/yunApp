package com.yunshen.yunapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yunshen.yunapp.model.entity.NavigationItem


@Composable
fun MainFrame(onNavigateToArticle: () -> Unit = {}) {
    var currentNavigationIndex by remember {
        mutableIntStateOf(0)
    }
    val items = listOf(
        NavigationItem("主页", Icons.Filled.Home),
        NavigationItem("详情", Icons.Filled.Build),
        NavigationItem("我的", Icons.Filled.AccountBox),
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        }, icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        }, label = {
                            Text(text = navigationItem.title)
                        })
                }
            }
        }
    ) { //这个box是防止遮住
        Box(modifier = Modifier.padding(it)){
            when(currentNavigationIndex){
                0 -> IndexScreen(onNavigateToArticle = onNavigateToArticle)
                1 -> DetailScreen()
                2 -> TaskScreen()
            }
        }
    }
}


@Preview(
    showSystemUi = true
)
@Composable
fun MainFramePreview(){
    MainFrame()
}