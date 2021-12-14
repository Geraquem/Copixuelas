package com.mmfsin.copixuelas.quepreferirias

import android.view.View
import com.mmfsin.copixuelas.quepreferirias.QuePrefeririasData.getDilemmas
import kotlinx.android.synthetic.main.fragment_quepreferirias.*

class QuePrefeririasPresenter(var quePrefeririasView: QuePrefeririasView) {

    fun setUpArray(): ArrayList<Int> {
        val list = getDilemmas()
        val indexList = ArrayList<Int>()
        for (i in list.indices) {
            indexList.add(i)
        }

        indexList.shuffle()
        return indexList
    }

    fun setUpText(){
        quePrefeririasView.setUpText()
    }

    fun checkButtons(numDilemma: Int, size: Int){
        when {
            (numDilemma <= 0) -> {
                quePrefeririasView.prevButton(false)
                quePrefeririasView.nextButton(true)
            }
            (numDilemma == size - 1) -> {
                quePrefeririasView.prevButton(true)
                quePrefeririasView.nextButton(false)
            }
            else -> {
                quePrefeririasView.prevButton(true)
                quePrefeririasView.nextButton(true)
            }
        }
    }
}