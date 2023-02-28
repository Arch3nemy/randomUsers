package com.alacrity.randomUsers.api

import com.alacrity.randomUsers.entity.ApiResponse
import com.alacrity.randomUsers.exceptions.RandomUsersException
import com.alacrity.randomUsers.retrofit.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/api")
    suspend fun loadUsersWithAmount(@Query("results") amount: Int): NetworkResponse<ApiResponse, RandomUsersException>

}