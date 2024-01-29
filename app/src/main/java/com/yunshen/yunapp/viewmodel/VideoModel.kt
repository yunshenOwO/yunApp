package com.yunshen.yunapp.viewmodel

import androidx.lifecycle.ViewModel
import com.yunshen.yunapp.model.entity.VideoEntity

class VideoModel:ViewModel() {
    var list = listOf(
        VideoEntity(title = "视频标题","视频课程", "00:02:00", "https://w.wallhaven.cc/full/6d/wallhaven-6d73x6.jpg"),
        VideoEntity(title = "视频标题2","视频课程", "00:02:00", "https://w.wallhaven.cc/full/vq/wallhaven-vqvgmp.jpg"),
        VideoEntity(title = "视频标题3","视频课程", "00:02:00", "https://w.wallhaven.cc/full/p9/wallhaven-p95rke.jpg"),
        VideoEntity(title = "视频标题4","视频课程", "00:02:00", "https://w.wallhaven.cc/full/ex/wallhaven-exrd7k.jpg"),
        VideoEntity(title = "视频标题5","视频课程", "00:02:00", "https://w.wallhaven.cc/full/jx/wallhaven-jxrmmy.jpg"),
        VideoEntity(title = "视频标题6","视频课程", "00:02:00", "https://w.wallhaven.cc/full/ex/wallhaven-exrj5r.jpg"),
        VideoEntity(title = "视频标题7","视频课程", "00:02:00", "https://w.wallhaven.cc/full/6d/wallhaven-6d7xmx.jpg"),
        VideoEntity(title = "视频标题8","视频课程", "00:02:00", "https://w.wallhaven.cc/full/3l/wallhaven-3lgjrd.png"),
    )
        private set
}