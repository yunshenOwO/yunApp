package com.yunshen.yunapp.model.service

import com.yunshen.yunapp.model.NetWork
import com.yunshen.yunapp.model.entity.CategoryResponse
import retrofit2.http.GET

interface HomeService {
    @GET("category/list")
    suspend fun category():CategoryResponse

    companion object{
        fun instance():HomeService{
            return NetWork.createService(HomeService::class.java)
        }
    }
}