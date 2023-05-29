package com.mmfsin.copixuelas.presentation.main

import com.mmfsin.copixuelas.data.local.getDataIntroPhrases

class MainPresenter(var mainView: MainView) {

    fun showIntroPhrase() {
        val list = getDataIntroPhrases()
        val random = (list.indices).random()
        mainView.showIntroPhrase(list[random])
    }
}