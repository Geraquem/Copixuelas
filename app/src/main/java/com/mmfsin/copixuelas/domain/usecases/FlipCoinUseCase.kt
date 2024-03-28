package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.CoinResult
import javax.inject.Inject

class FlipCoinUseCase @Inject constructor(
) : BaseUseCaseNoParams<CoinResult>() {

    override suspend fun execute(): CoinResult {
        return CoinResult.CRUZ
    }
}

/*


fun getCoinResult(): CoinResult {
    // 1 2 3 4 5 6 7 - 8 9 10 11 12
    val rand = (1..12).random()
//    return if (rand % 2 == 0) CRUZ else CARA
    return if (rand in 1..7) CRUZ else CARA
}

 */