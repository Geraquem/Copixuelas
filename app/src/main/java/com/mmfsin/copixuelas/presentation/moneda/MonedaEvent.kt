package com.mmfsin.copixuelas.presentation.moneda

import com.mmfsin.copixuelas.domain.models.CoinResult

sealed class MonedaEvent {
    class GetData(val data: List<String>) : MonedaEvent()
    class FlipCoin(val result: CoinResult) : MonedaEvent()
    object SWW : MonedaEvent()
}