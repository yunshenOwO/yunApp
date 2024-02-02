package com.yunshen.yunapp.model.entity

data class Category(
    val title:String,
    val id:String
)

data class CategoryResponse(
    var data:List<Category>
):BaseResponse()