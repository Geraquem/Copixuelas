package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.BotellaSpins
import com.mmfsin.copixuelas.domain.models.CoinResult
import javax.inject.Inject

class GetBotellaSpinUseCase @Inject constructor() : BaseUseCaseNoParams<BotellaSpins>() {

    override suspend fun execute(): BotellaSpins {
        val spins = (2880..9000).random()
        val duration = (2000..5000).random()
        return BotellaSpins(spins.toFloat(), duration.toLong())
    }
}