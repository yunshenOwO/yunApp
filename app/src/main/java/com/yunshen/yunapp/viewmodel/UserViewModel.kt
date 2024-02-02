package com.yunshen.yunapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunshen.yunapp.model.service.UserInfoManager
import com.yunshen.yunapp.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserViewModel(content:Context):ViewModel(){

    private val userInfoManager = UserInfoManager(content)

    init {
        viewModelScope.launch {
            val username = userInfoManager.userNames.firstOrNull()
            userInfo = if (username?.isNotEmpty() == true){
                UserInfoEntity(username)
            }else {
                null
            }
        }
    }
    var userInfo:UserInfoEntity? = null
        private set

    val login:Boolean
        get() {
            return userInfo != null
        }

    fun login(onBack : () -> Unit){
        userInfo = UserInfoEntity("user001")
        //模拟网络请求数据回传
        viewModelScope.launch {
            userInfoManager.save("user001")
        }
        onBack()

    }

    fun clear(){
        viewModelScope.launch {
            userInfoManager.logout() //退出登录
        }
    }
}