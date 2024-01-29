package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.yunshen.yunapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@Composable
fun Notification(vm:MainViewModel) {

    val virtualCount = Int.MAX_VALUE
    val actualCount = vm.notifications.size
    val initCountIndex = virtualCount / 2
    val pageSate = rememberPagerState(initialPage = initCountIndex)
    val currency = rememberCoroutineScope()
    val timer = Timer()
    timer.schedule(object : TimerTask(){
        override fun run() {
            currency.launch { pageSate.animateScrollToPage(pageSate.currentPage + 1) }
        }

    }, 3000, 3000)

    Row (modifier = Modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0xFF448486))
        .height(45.dp)
        .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = "最新通知",
            color = Color(0xff149EE7),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        VerticalPager(count = virtualCount,
            modifier = Modifier.weight(1f),
            state = pageSate,
            horizontalAlignment = Alignment.Start) {
            index ->
            val actualIndex = (index - initCountIndex).floorMod(actualCount)// val actualIndex = index - (index.floorDiv(actualCount)) * actualCount
            Text(text = vm.notifications[actualIndex],
                color = Color(0xff333333),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(text = "更多",
            color = Color(0xff666666),
            fontSize = 14.sp)
    }
}