package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.CoinResult
import com.mmfsin.copixuelas.domain.models.CoinResult.CARA
import com.mmfsin.copixuelas.domain.models.CoinResult.CRUZ
import javax.inject.Inject

class FlipCoinUseCase @Inject constructor(
) : BaseUseCaseNoParams<CoinResult>() {

    override suspend fun execute(): CoinResult {
        return try {
            val time = System.currentTimeMillis().toString()
            val last = time.last().digitToInt()
            if (last in 0..6) CRUZ else CARA

        } catch (e: Exception) {
            val rand = (1..12).random()
            if (rand in 1..7) CRUZ else CARA
        }
    }
}