package com.mmfsin.copixuelas.averquepasa

import com.mmfsin.copixuelas.averquepasa.data.AVQPData.getPruebas

class AVQPInteractor(val listener: OnDataRetrieved) {

    fun setUpArray(){
        val list = getPruebas()
        val indexList = ArrayList<Int>()
        for (i in 0..list.size) {
            indexList.add(i)
        }
        for(i in indexList.size downTo 1){
            val position = (0..i).random()
            val tmp = indexList[i-1]
            indexList[i-1] = indexList[position]
            indexList[position] = tmp
        }
        listener.arrayMixed(indexList)
    }

    interface OnDataRetrieved {
        fun arrayMixed(list: ArrayList<Int>)
    }
}