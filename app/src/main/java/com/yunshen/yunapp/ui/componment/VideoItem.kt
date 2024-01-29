package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.yunshen.yunapp.model.entity.VideoEntity

@Composable
fun VideoItem(videoItem:VideoEntity) {
    val constantSet = ConstraintSet{
        val title = createRefFor("title")
        val cover = createRefFor("cover")
        val type = createRefFor("type")
        val duration = createRefFor("duration")
        val divider = createRefFor("divider")
        constrain(cover){
            start.linkTo(parent.start)
            centerVerticallyTo(parent)
            width = Dimension.value(115.5.dp)
        }

        constrain(title){
            start.linkTo(cover.end, margin = 8.dp)
            width = Dimension.fillToConstraints
            end.linkTo(parent.end)
        }

        constrain(type){
            start.linkTo(title.start)
            bottom.linkTo(parent.bottom)
        }

        constrain(duration){
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        constrain(divider){
            bottom.linkTo(parent.bottom, margin = (-16).dp)
        }
    }
    ConstraintLayout(constantSet, modifier = Modifier.fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp, bottom = 16.dp, top = 8.dp)) {
        AsyncImage(model = videoItem.imageUrl, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .layoutId("cover")
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp)))

        Text(text = videoItem.title,
            fontSize = 16.sp,
            color = Color(0xff666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("title")
        )
        Text(text = videoItem.type,
            fontSize = 16.sp,
            color = Color(0xff999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("type"))

        Text(text = videoItem.duration,
            fontSize = 16.sp,
            color = Color(0xff999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("duration"))

        Divider(modifier = Modifier.layoutId("divider")
            .padding(vertical = 8.dp))
    }
}