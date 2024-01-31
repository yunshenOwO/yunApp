package com.yunshen.yunapp.model.compositionloacl

import androidx.compose.runtime.compositionLocalOf
import com.yunshen.yunapp.viewmodel.UserViewModel

val localUserLogin = compositionLocalOf<UserViewModel> { error("User ViewModel not find") }