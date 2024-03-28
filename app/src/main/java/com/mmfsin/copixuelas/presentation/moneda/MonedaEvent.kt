package com.mmfsin.copixuelas.presentation.moneda

sealed class MonedaEvent {
    class GetData(val data: List<String>) : MonedaEvent()
    object SWW : MonedaEvent()
}