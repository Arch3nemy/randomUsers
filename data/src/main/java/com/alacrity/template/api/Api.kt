package com.alacrity.template.api

import com.alacrity.template.entity.ApiResponse
import com.alacrity.template.exceptions.TemplateException
import com.alacrity.template.retrofit.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/todos/{id}")
    suspend fun getFactAboutNumber(@Path("id") id: Int): NetworkResponse<ApiResponse, TemplateException>

}