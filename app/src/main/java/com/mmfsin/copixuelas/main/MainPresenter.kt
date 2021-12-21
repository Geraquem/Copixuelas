package com.mmfsin.copixuelas.main

import android.app.AlertDialog
import android.content.Context
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.main.data.MainData

class MainPresenter(var mainView: MainView) {

    fun showIntroPhrase() {
        val list = MainData.getDataIntroPhrases()
        val random = (list.indices).random()
        mainView.showIntroPhrase(list[random])
    }

    fun showDialog(context: Context) {
        val builder: AlertDialog.Builder = context.let { AlertDialog.Builder(it) }
        builder.setView(R.layout.alert_dialog)
        builder.apply {
            setPositiveButton(R.string.understood) { _, _ -> }
        }
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }
}