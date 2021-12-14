package com.mmfsin.copixuelas.moneda

import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPData
import com.mmfsin.copixuelas.moneda.MonedaData.getPreguntas

class MonedaPresenter(private val monedaView: MonedaView): Fragment() {

    fun setUpArray(): ArrayList<Int> {
        val list = getPreguntas()
        val indexList = ArrayList<Int>()
        for (i in list.indices) {
            indexList.add(i)
        }

        indexList.shuffle()
        return indexList
    }

    fun showQuestion() {
        monedaView.showQuestion()
    }

    fun showCoin() {
        monedaView.showCoin()
    }

    fun resetCoin() {
        monedaView.resetCoin()
    }

    fun coinPressed() {
        val random = (1..10).random()
        if (random % 2 == 0) {
            flipCoin(R.drawable.ic_moneda_cara, "CARA")
        } else {
            flipCoin(R.drawable.ic_moneda_cruz, "CRUZ")
        }
    }

    private fun flipCoin(imageId: Int, result: String) {
        monedaView.flipCoin(imageId, result)
    }
}