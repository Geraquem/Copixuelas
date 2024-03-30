package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import javax.inject.Inject

class GetBotellaSpinUseCase @Inject constructor() : BaseUseCaseNoParams<Long>() {

    override suspend fun execute(): Long {
        return 0
    }
}