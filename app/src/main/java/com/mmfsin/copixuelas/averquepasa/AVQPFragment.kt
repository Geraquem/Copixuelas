package com.mmfsin.copixuelas.averquepasa

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPData.getPruebas
import com.mmfsin.copixuelas.IFragmentCommunication
import kotlinx.android.synthetic.main.fragment_avqp.*

class AVQPFragment(private val listener: IFragmentCommunication) : Fragment() {

    private val presenter by lazy { AVQPPresenter() }

    private val pruebas = getPruebas()
    private var indexList = ArrayList<Int>()
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

        showInstructions()

        textPhrase.text = getText(R.string.avqpStart)

        indexList = presenter.setUpArray()
        info.setOnClickListener { showInstructions() }
        screen.setOnClickListener { showPhrase() }
    }

    private fun showPhrase() {
        numPhrase++
        val phrase = pruebas[indexList[numPhrase]]
        checkIfIsRule(phrase)
        textPhrase.text = phrase
        if (numPhrase == pruebas.size - 1) {
            numPhrase = -1
        }
    }

    private fun checkIfIsRule(phrase: String){
        if(phrase.contains(getString(R.string.rule))){
            val typeFace: Typeface? = ResourcesCompat.getFont(mContext, R.font.emilys)
            textPhrase.typeface = typeFace
        }else{
            val typeFace: Typeface? = ResourcesCompat.getFont(mContext, R.font.boogaloo)
            textPhrase.typeface = typeFace
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun showInstructions(){
        listener.showFragmentInstructions(listener, getString(R.string.averquepasa))
    }
}