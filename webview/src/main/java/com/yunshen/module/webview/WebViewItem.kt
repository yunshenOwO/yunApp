package com.yunshen.module.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewItem(state: WebViewState){
    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    LaunchedEffect(webView, state){
        with(state){
            webView?.handEvent()
        }
    }
    AndroidView(factory = {
        context ->
        WebView(context).apply {
            webChromeClient = object :WebChromeClient(){
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    state.pageTitle = title
                }
            }

            webViewClient = object :WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    state.pageTitle = null
                }
            }
            with(settings){
                javaScriptEnabled = true
            }
        }.also { webView = it  }
    }){
        view ->
      when(val content = state.content){
          is WebContent.Url -> {
              val url = content.url
              if (url.isNotEmpty() && url != view.url){
                  view.loadUrl(content.url)
              }
          }
          is WebContent.Data -> {
              view.loadDataWithBaseURL(content.baseUrl,
                  content.data,
                  null,
                  "utf-8",
                  null)
          }
      }
    }
}
sealed class WebContent{
    data class Url(val url:String): WebContent()
    data class Data(val data:String, val baseUrl:String? =null): WebContent()
}

class WebViewState(webContent: WebContent){
    internal var content by mutableStateOf(webContent)
    internal var pageTitle :String? by mutableStateOf(null) //让外部访问不了这个属性
    //共享流数据类型
    private class Event(val type: EventType, val args:String, val callback: ((String) -> Unit)?)

    enum class EventType{
        EVALUATE_JAVASCRIPT//执行js方法

    }
    //共享流
    private val events:MutableSharedFlow<Event> = MutableSharedFlow()
    suspend fun WebView.handEvent() :Unit= withContext(Dispatchers.Main){
        events.collect{
            event ->
            when(event.type){
                EventType.EVALUATE_JAVASCRIPT -> evaluateJavascript(event.args, event.callback)
            }

        }
    }
    suspend fun evaluateJavascript(script:String, resultCallback:((String) -> Unit)? = {}){
        val event = Event(EventType.EVALUATE_JAVASCRIPT, script, resultCallback)
        events.emit(event) //推送流
    }
}

@Composable
fun rememberWebViewState(url:String) = remember(key1 = url) {
    WebViewState(WebContent.Url(url))
}

@Composable
fun rememberWebViewState(data:String, baseUrl:String? =null) = remember(key1 = data, key2 = baseUrl) {
    WebViewState(WebContent.Data(data, baseUrl))
}