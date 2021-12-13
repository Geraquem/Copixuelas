package com.mmfsin.copixuelas.main

class MainPresenter(var mainView: MainView?): MainInteractor.IMainInteractor {

    private val interactor = MainInteractor(this)

    fun showIntroPhrase(){
        interactor.getIntroPhrase()
    }

    override fun onSuccess(phrase: String) {
        mainView?.showIntroPhrase(phrase)
    }
}