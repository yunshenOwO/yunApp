package com.yunshen.yunapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.yunshen.yunapp.model.entity.Category
import com.yunshen.yunapp.model.entity.DataType
import com.yunshen.yunapp.model.entity.SwiperEntity

class MainViewModel:ViewModel() {
    val category by mutableStateOf(
        listOf(
            Category("思想启蒙"),
            Category("billbil"),
            Category("QQ")
        )
    )
    /**
     * 当前分类下标
     */
    var categoryindex by mutableIntStateOf(0)
        private set

    //更新分类下标
    fun updateCatgoryIndex(index:Int){
        categoryindex = index
    }

    val types by mutableStateOf(
        listOf(DataType("文章", Icons.Default.Menu),
            DataType("视频", Icons.Default.PlayArrow))
    )

    var currentypeIndex by mutableIntStateOf(0)
        private set

    /**
     * 更新类型下标
     */
    fun updateTypeIndex(index:Int){
        currentypeIndex = index
    }

    val swiperData = listOf(
        SwiperEntity("https://w.wallhaven.cc/full/l8/wallhaven-l8rory.jpg"),
        SwiperEntity("https://w.wallhaven.cc/full/o5/wallhaven-o5woyp.jpg"),
        SwiperEntity("https://w.wallhaven.cc/full/m3/wallhaven-m3rm11.png"),
        SwiperEntity("https://w.wallhaven.cc/full/p9/wallhaven-p955gm.jpg")
        )

    val notifications = listOf(
        "您可以实现一次用户界面，然后将其用于您的所有目标平台 - iOS、Android、桌面和 Web。"
    )
}