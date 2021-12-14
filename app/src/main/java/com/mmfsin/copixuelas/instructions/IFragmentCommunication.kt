package com.mmfsin.copixuelas.instructions

interface IFragmentCommunication {
    fun closeFragment()
    fun showFragmentInstructions(listener: IFragmentCommunication, id: String)
}