package com.mmfsin.copixuelas.presentation.moneda

import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CARA
import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CRUZ

fun getCoinResult(): CoinResult {
    val rand = (0..10).random()
    return if (rand % 2 == 0) CRUZ else CARA
}

enum class CoinResult {
    CARA,
    CRUZ
}