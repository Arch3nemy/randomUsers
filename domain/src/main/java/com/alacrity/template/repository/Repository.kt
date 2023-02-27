package com.alacrity.template.repository

import com.alacrity.template.entity.ApiResponse

interface Repository {

    suspend fun getResponse(number: Int): ApiResponse

}