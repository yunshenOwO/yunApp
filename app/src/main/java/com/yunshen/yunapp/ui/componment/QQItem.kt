package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun QQItem(){
    LazyColumn {
        item{
                Card(modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()) {
                    Row (horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)){
                        AsyncImage(
                            model = "https://w.wallhaven.cc/full/ex/wallhaven-exrj5r.jpg",
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(54.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column (modifier = Modifier.padding(8.dp)){
                            Text(text = "编程语言交流群", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text(text = "本子喵，我是你的左孩子啊！: 云猫猫我喜欢你", fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }

                    }
                }
        }
    }
}