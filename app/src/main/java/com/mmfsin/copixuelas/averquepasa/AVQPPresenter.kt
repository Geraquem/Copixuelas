package com.mmfsin.copixuelas.averquepasa

import com.mmfsin.copixuelas.averquepasa.AVQPData.getPruebas

class AVQPPresenter {

    fun setUpArray(): ArrayList<Int> {
        val list = getPruebas()
        val indexList = ArrayList<Int>()
        for (i in list.indices) {
            indexList.add(i)
        }

        indexList.shuffle()
        return indexList
    }
}