package com.mmfsin.copixuelas.main

import android.app.AlertDialog
import android.content.Context
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.instructions.InstructionsFragment
import com.mmfsin.copixuelas.main.data.MainData

class MainPresenter(var mainView: MainView) {

    fun showIntroPhrase() {
        val list = MainData.getDataIntroPhrases()
        val random = (list.indices).random()
        mainView.showIntroPhrase(list[random])
    }
}