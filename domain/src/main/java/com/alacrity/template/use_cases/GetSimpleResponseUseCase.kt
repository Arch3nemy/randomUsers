package com.alacrity.template.use_cases

import com.alacrity.template.entity.ApiResponse

interface GetSimpleResponseUseCase {

    suspend operator fun invoke(number: Int): ApiResponse

}