package com.yunshen.yunapp.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object NetWork {
    private const val BashURL = "https://mock.apipost.cn/app/mock/project/ced69cf2-9206-4a42-895e-dd7442a888df/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BashURL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            )
        ).build()

    fun <T> createService(clazz: Class<T>):T{
        return retrofit.create(clazz)
    }

}