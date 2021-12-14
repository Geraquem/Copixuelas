package com.mmfsin.copixuelas.moneda

import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R

class MonedaPresenter(private val monedaView: MonedaView): Fragment() {

    fun coinPressed(){
        val random = (1..6).random()
        if (random % 2 == 0) {
            flipCoin(R.drawable.ic_moneda_cara, getString(R.string.cara))
        } else {
            flipCoin(R.drawable.ic_moneda_cruz, getString(R.string.cruz))
        }
    }

    private fun flipCoin(imageId: Int, result: String){
        monedaView.flipCoin(imageId, result)
    }
}