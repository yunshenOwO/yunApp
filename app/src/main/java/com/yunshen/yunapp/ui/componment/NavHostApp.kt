package com.yunshen.yunapp.ui.componment

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yunshen.yunapp.ui.navigation.Destination
import com.yunshen.yunapp.ui.screens.ArticleDetailScreen
import com.yunshen.yunapp.ui.screens.MainFrame

/**
 * 导航控制器
 */
@Composable
fun NavHostApp(){
    val navController = rememberNavController()
    //路由入口
    NavHost(navController = navController, startDestination = Destination.HomeFrame.route, builder = {
        composable(route = Destination.HomeFrame.route, enterTransition ={
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        }){
            MainFrame(onNavigateToArticle = {
                navController.navigate(Destination.ArticleDetail.route)
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
    })
}
