package com.mmfsin.copixuelas.queprefieres

import com.mmfsin.copixuelas.queprefieres.QuePrefieresData.getDilemmas

class QuePrefieresPresenter(var quePrefieresView: QuePrefieresView) {

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
        quePrefieresView.setUpText()
    }

    fun checkButtons(numDilemma: Int, size: Int){
        when {
            (numDilemma <= 0) -> {
                quePrefieresView.prevButton(false)
                quePrefieresView.nextButton(true)
            }
            (numDilemma == size - 1) -> {
                quePrefieresView.prevButton(true)
                quePrefieresView.nextButton(false)
            }
            else -> {
                quePrefieresView.prevButton(true)
                quePrefieresView.nextButton(true)
            }
        }
    }
}