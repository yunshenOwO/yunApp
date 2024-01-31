package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yunshen.yunapp.model.entity.VideoEntity
import com.yunshen.yunapp.viewmodel.VideoModel

@Composable
fun BilBilItem(goToVideo: () -> Unit,videoModel: VideoModel){
    LazyColumn {
        items(videoModel.list){
                item: VideoEntity ->
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                AsyncImage(model = item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(7 / 3f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)).clickable { goToVideo() },
                    contentScale = ContentScale.Crop)
                Row(horizontalArrangement = Arrangement.SpaceAround){
                    Card(modifier = Modifier
                        .padding(3.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF94B1FE))){
                        Text(text = item.title, modifier = Modifier.padding(5.dp), fontSize = 16.sp)
                    }
                    Card(modifier = Modifier
                        .padding(3.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF94B1FE))){
                        Text(text = item.type, modifier = Modifier.padding(5.dp), fontSize = 16.sp)
                    }

                    Card(modifier = Modifier
                        .padding(3.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF94B1FE))){
                        Text(text = item.duration, modifier = Modifier.padding(5.dp), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}