package com.mmfsin.copixuelas.moneda

interface MonedaView {
    fun showQuestion()
    fun showCoin()
    fun resetCoin()
    fun flipCoin(imageId: Int, result: String)
}