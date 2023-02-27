package com.alacrity.template.use_cases

import com.alacrity.template.repository.Repository
import javax.inject.Inject

class GetSimpleResponseUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetSimpleResponseUseCase {

    override suspend fun invoke(number: Int) = repository.getResponse(number)

}