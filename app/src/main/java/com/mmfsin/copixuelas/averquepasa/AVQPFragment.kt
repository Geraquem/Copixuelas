package com.mmfsin.copixuelas.averquepasa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPData.getPruebas
import com.mmfsin.copixuelas.instructions.IFragmentComunication
import com.mmfsin.copixuelas.instructions.InstructionsFragment
import kotlinx.android.synthetic.main.fragment_avqp.*

class AVQPFragment(private val listener: IFragmentComunication) : Fragment() {

    private val presenter by lazy { AVQPPresenter() }
    private var indexList = ArrayList<Int>()

    private val pruebas = getPruebas()
    private var numPhrase = -1

    private lateinit var mContext: Context

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

        showInstructions()

        indexList = presenter.setUpArray()
        info.setOnClickListener {showInstructions()}
        screen.setOnClickListener { showPhrase() }
    }

    private fun showInstructions(){
        listener.showFragmentInstructions(InstructionsFragment(listener, "avqp"))
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
}