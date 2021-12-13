package com.mmfsin.copixuelas.averquepasa

class AVQPPresenter {

    fun setUpArray() : ArrayList<Int>{
        val list = AVQPData.getPruebas()
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
        return indexList
    }
}