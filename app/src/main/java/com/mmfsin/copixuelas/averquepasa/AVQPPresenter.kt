package com.mmfsin.copixuelas.averquepasa

class AVQPPresenter(var avqpView: AVQPView?) : AVQPInteractor.OnDataRetrieved {

    private val interactor = AVQPInteractor(this)

    fun setUpArray() {
        interactor.setUpArray()
    }

    override fun arrayMixed(list: ArrayList<Int>) {
        avqpView?.setUpArray(list)
    }

    fun onDestroy() {
        avqpView = null
    }
}