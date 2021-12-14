package com.mmfsin.copixuelas.main

import com.mmfsin.copixuelas.main.data.MainData

class MainPresenter(var mainView: MainView) {

    fun showIntroPhrase() {
        val list = MainData.getDataIntroPhrases()
        val random = (list.indices).random()
        mainView.showIntroPhrase(list[random])
    }
}