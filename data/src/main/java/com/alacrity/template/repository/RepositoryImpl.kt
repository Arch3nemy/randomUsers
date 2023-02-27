package com.alacrity.template.repository

import com.alacrity.template.api.Api
import com.alacrity.template.entity.ApiResponse
import com.alacrity.template.exceptions.TemplateException
import com.alacrity.template.retrofit.NetworkResponse
import javax.inject.Inject
import timber.log.Timber

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {

    override suspend fun getResponse(number: Int): ApiResponse {
        when (val call = api.getFactAboutNumber(number)) {
            is NetworkResponse.Success -> {
                val data = call.body
                Timber.d("data $data")
                return call.body
            }
            is NetworkResponse.ApiError -> {
                throw TemplateException("Api error")
            }
            is NetworkResponse.NetworkError -> {
                throw TemplateException("Network error")
            }
            is NetworkResponse.UnknownError -> {
                throw TemplateException("Unknown error")
            }
        }
    }

}