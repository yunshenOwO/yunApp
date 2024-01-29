package com.yunshen.yunapp.ui.componment

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.yunshen.yunapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwiperContent(vm:MainViewModel) {
    //虚拟页数，实际页数
    val virtualCount = Int.MAX_VALUE
    val actualCount = vm.swiperData.size
    val initCountIndex = virtualCount / 2
    val pageSate = rememberPagerState(initialPage = initCountIndex)
    val currency = rememberCoroutineScope()
    val timer = Timer()
    timer.schedule(object :TimerTask(){
        override fun run() {
            currency.launch { pageSate.animateScrollToPage(pageSate.currentPage + 1) }
        }

    }, 3000, 3000)

    HorizontalPager(count = virtualCount,
        state = pageSate,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))) { index ->
        val actualIndex = (index - initCountIndex).floorMod(actualCount)//index - (index.floorDiv(actualCount)) * actualCount
        AsyncImage(model =vm.swiperData[actualIndex].imgUrl,
            contentDescription =null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f),
            contentScale = ContentScale.Crop)
    }
}

fun Int.floorMod(other:Int) :Int = when(other){
    0 -> this
    else -> this - floorDiv(other) * other
}
