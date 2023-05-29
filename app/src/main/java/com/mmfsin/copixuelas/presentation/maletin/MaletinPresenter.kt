package com.mmfsin.copixuelas.presentation.maletin

import android.widget.ImageView

class MaletinPresenter(private val maletinView: MaletinView) {

    fun replayGame(){
        maletinView.replayGame()
    }

    fun setTag(maletin: ImageView, tag: String) {
        maletin.tag = tag
    }
}