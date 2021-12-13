package com.mmfsin.copixuelas.main

import com.mmfsin.copixuelas.main.data.MainData.getDataIntroPhrases
import kotlin.random.Random

class MainInteractor(val listener: IMainInteractor) {

    fun getIntroPhrase(){
        val list = getDataIntroPhrases()
        val random = (list.indices).random()
        listener.onSuccess(list[random])
    }

    interface IMainInteractor{
        fun onSuccess(phrase: String){}
    }
}