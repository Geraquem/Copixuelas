package com.mmfsin.copixuelas.presentation.moneda

import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CARA
import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CRUZ

fun getCoinResult(): CoinResult {
    // 1 2 3 4 5 6 7 - 8 9 10 11 12
    val rand = (1..12).random()
//    return if (rand % 2 == 0) CRUZ else CARA
    return if (rand in 1..7) CRUZ else CARA
}

enum class CoinResult {
    CARA,
    CRUZ
}