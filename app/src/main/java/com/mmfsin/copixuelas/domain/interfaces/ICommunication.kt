package com.mmfsin.copixuelas.domain.interfaces

interface ICommunication {
    fun closeFragment()
    fun showFragmentInstructions(listener: ICommunication, id: String)
    fun showAd()
}