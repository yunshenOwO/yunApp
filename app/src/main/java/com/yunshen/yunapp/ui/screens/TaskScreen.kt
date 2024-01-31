package com.yunshen.yunapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yunshen.yunapp.R
import com.yunshen.yunapp.model.compositionloacl.localUserLogin
import com.yunshen.yunapp.ui.componment.TopAppBar


@Composable
fun TaskScreen() {
    val menu = listOf(MenuItem(R.drawable.intergration, "学习积分"),
        MenuItem(R.drawable.browse, "浏览记录"),
        MenuItem(R.drawable.questtion, "常见问题"),
        MenuItem(R.drawable.version, "版本信息"),
        MenuItem(R.drawable.setting, "个人设置"),
        MenuItem(R.drawable.callme, "联系我们")
        )
    Column (modifier = Modifier){
        TopAppBar {
            Text(text = "我的", fontSize = 18.sp, color = Color.White)
        }
        LazyColumn(modifier = Modifier.padding(8.dp)){
            //头像部分
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 24.dp)){
                    Image(painter = painterResource(id = R.drawable.yunshen), contentDescription =null,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop)

                    Column (verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(62.dp)){
                        (if (localUserLogin.current.login) localUserLogin.current.userInfo?.username else "未登录")?.let {
                            Text(text = it, color = Color(0xff333333),
                                fontSize = 18.sp)
                        }
                        Text(text = "已坚持学习0天", color = Color(0xff333333), fontSize = 12.sp)
                    }
                }
            }
            //菜单部分
            itemsIndexed(menu){index, it ->
                if (index == 3){
                    Box(modifier = Modifier
                        .height(16.dp)
                        .background(Color(0xfff5f5f5))
                        .fillMaxWidth())
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)) {
                    Icon(painter = painterResource(id = it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = Color(0xFF9F57E6)
                    )
                    Column(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)) {
                            Text(text = it.title,
                                color = Color(0xff333333),
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f))
                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(13.dp),
                                tint = Color.Gray
                            )
                        }
                        Divider()
                    }
                }

            }
        }
    }

}

data class MenuItem(@DrawableRes val icon:Int, val title:String)

@Preview(
    showSystemUi = true
)
@Composable
fun TskScreenPreview(){
    TaskScreen()
}