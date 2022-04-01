package com.mmfsin.copixuelas

interface IFragmentCommunication {
    fun closeFragment()
    fun showFragmentInstructions(listener: IFragmentCommunication, id: String)
}