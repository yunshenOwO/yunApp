package com.yunshen.yunapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yunshen.yunapp.R
import com.yunshen.yunapp.model.compositionloacl.localUserLogin


@Composable
fun LogInScreen(onBack:() -> Unit) {
//    var screenWidth:Float
//    var screenHigh:Float
//    with(LocalDensity.current){
//        screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()
//        screenHigh = LocalConfiguration.current.screenHeightDp.dp.toPx()
//    }
    val userViewModel = localUserLogin.current
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }
    BoxWithConstraints (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.yunshen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop ) //放大裁剪
        //右上往左下渐变
        Box (modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(  //从哪个点开始，哪个点结束
                    listOf(
                        Color(0xffbb8378), Color.Transparent //透明
                    ), start = Offset(constraints.maxWidth.toFloat(), 0f),
                    end = Offset(x = 0f, constraints.maxHeight.toFloat())
                )
            ))
        //右下往左上渐变
        Box (modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF954EC2), Color.Transparent),
                    start = Offset(x = 0f, constraints.maxHeight.toFloat()),
                    end = Offset(constraints.maxWidth.toFloat(), 0f) //透明
                )
            ))

        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = "用户登录", color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)

            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween){
                TextField(value = username,
                    onValueChange = {
                        username = it },
                    singleLine = true, //单行
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(text = "用户名", fontSize = 14.sp, color = Color.White)
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        cursorColor = Color.White

                    ), modifier = Modifier.padding(8.dp))

                TextField(value = password,
                    onValueChange = {
                        password = it },
                    singleLine = true, //单行
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Password,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(text = "密码", fontSize = 14.sp, color = Color.White)
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White

                    ), modifier = Modifier.padding(8.dp),
                    trailingIcon = {
                        Icon(imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null, tint = Color.White, modifier = Modifier.clickable {
                                showPassword = !showPassword
                            })
                    })

                TextButton(onClick = {userViewModel.login(onBack = onBack)}, modifier = Modifier.padding(8.dp)) {
                    Text(text = "登录", color = Color.White)
                }
            }

            TextButton(onClick = {

            }) {
                Text(text = "还没有账号? 点击注册", color = Color.Gray, fontSize = 16.sp, modifier = Modifier.clickable {
                    onBack()
                })
            }
        }
    }

}
