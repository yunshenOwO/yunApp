package com.yunshen.yunapp.ui.componment

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yunshen.yunapp.model.compositionloacl.localUserLogin
import com.yunshen.yunapp.ui.navigation.Destination
import com.yunshen.yunapp.ui.screens.ArticleDetailScreen
import com.yunshen.yunapp.ui.screens.BilBilVideoScreen
import com.yunshen.yunapp.ui.screens.LogInScreen
import com.yunshen.yunapp.ui.screens.MainFrame
import com.yunshen.yunapp.ui.screens.VideoDetailScreen
import com.yunshen.yunapp.viewmodel.UserViewModel

/**
 * 导航控制器
 */
@Composable
fun NavHostApp(){
    val navController = rememberNavController() //控制器
    CompositionLocalProvider(localUserLogin provides UserViewModel(LocalContext.current)){
        val userViewModel = localUserLogin.current
    //路由入口
    NavHost(navController = navController,
        startDestination = Destination.HomeFrame.route,
        builder = {
        composable(route = Destination.HomeFrame.route, enterTransition ={
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        }){
            MainFrame(onNavigateToArticle = {
                navController.navigate(Destination.ArticleDetail.route)
            }, onNavigateToVideo = {
                navController.navigate(Destination.VideoDetail.route)
            }, goToVideo = {
                navController.navigate(Destination.BilBilVideo.route)
            }, onNavigateToStudy = {
                if (userViewModel.login){
                    Log.i("login", "NavHostApp: 登录成功")
                }else{
                    navController.navigate(Destination.LOGIN.route)
                }
            })
        }
        composable(Destination.ArticleDetail.route, enterTransition ={
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        }){
            ArticleDetailScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(Destination.VideoDetail.route, enterTransition ={
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        }){
            VideoDetailScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(Destination.BilBilVideo.route, enterTransition ={
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        }){
           BilBilVideoScreen(onBack = {
               navController.popBackStack()
           })
        }

        composable(Destination.LOGIN.route, enterTransition ={
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        }){
            LogInScreen(onBack = {
                navController.popBackStack()
            })
        }
    })
    }
}
