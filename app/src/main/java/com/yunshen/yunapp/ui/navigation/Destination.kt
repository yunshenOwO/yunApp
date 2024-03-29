package com.yunshen.yunapp.ui.navigation

sealed class Destination(val route:String) {
    //首页大框架

    data object HomeFrame:Destination("HomeFrame")
    //文章详情页

    data object ArticleDetail:Destination("ArticleDetail")

    data object VideoDetail:Destination("VideoDetail")

    data object BilBilVideo:Destination("BilBilVideo")

    data object LOGIN:Destination("LOGIN")
}