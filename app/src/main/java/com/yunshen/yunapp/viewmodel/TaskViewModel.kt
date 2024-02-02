package com.yunshen.yunapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random


class TaskViewModel : ViewModel(){
    var taskData by mutableStateOf("学习周期: 2022.01.01-2022.12.31")
        private set

    var pointOfYear by mutableIntStateOf(10000)
        private set

    var totalPointOfYear = 13500

    //计算学年积分进度
    var pointOfyearPercent by mutableFloatStateOf(0f)
        private set

    fun updatePointOfYear(){
        pointOfyearPercent = 220f * pointOfYear / totalPointOfYear
    }

    var pointOfWeak by mutableStateOf(listOf(
        4f,7f,15f,7f,3f,4f,5f
    ))
        private set

    //今日积分
    private val todayPoint = Random.nextInt(0, 15)

    val week = listOf("02.05","02.06","02.07","02.08","02.09","02.10","今日")

    //今日提醒文字
    var tips by mutableStateOf("今日获得0积分，快去完成以下任务")
        private set

    fun updateTips(){
        tips = when(todayPoint){
            0 -> "今日获得0积分，快去完成以下任务"
            in 1..14 ->{
                "今日获得 $todayPoint 积分，再接再厉"
            }
            else -> "太棒了!!都完成了"
        }
    }
}