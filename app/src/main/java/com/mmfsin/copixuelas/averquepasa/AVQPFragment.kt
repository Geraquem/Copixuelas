package com.mmfsin.copixuelas.averquepasa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.data.AVQPData.getPruebas
import kotlinx.android.synthetic.main.fragment_avqp.*

class AVQPFragment : Fragment(), AVQPView {

    private val presenter = AVQPPresenter(this)
    private var indexList = ArrayList<Int>()

    private val pruebas = getPruebas()
    private var numPhrase = -1

    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_avqp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //loading.visible = VISIBLE

        presenter.setUpArray()
        screen.setOnClickListener { showPhrase() }
    }

    private fun showPhrase() {
        numPhrase++
        textPhrase.text = pruebas[indexList[numPhrase]]
        if (numPhrase == pruebas.size - 1) {
            numPhrase = -1
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun showInstructions() {

    }

    override fun setUpArray(list: ArrayList<Int>) {
        indexList = list
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}