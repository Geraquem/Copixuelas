package com.mmfsin.copixuelas.moneda

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.IFragmentCommunication
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.moneda.MonedaData.getPreguntas
import kotlinx.android.synthetic.main.fragment_moneda.*

class MonedaFragment(private val listener: IFragmentCommunication) : Fragment(), MonedaView {

    private val presenter by lazy { MonedaPresenter(this) }

    private val preguntas = getPreguntas()
    private var indexList = ArrayList<Int>()
    private var numQuestion = 0

    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_moneda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showInstructions()

        indexList = presenter.setUpArray()

        showQuestion()
        presenter.showQuestion()

        info.setOnClickListener { showInstructions() }

        continueButton.setOnClickListener {
            presenter.resetCoin()
            presenter.showCoin()
        }

        coin.setOnClickListener { presenter.coinPressed() }

        againButton.setOnClickListener {
            againButton.visibility = View.GONE
            showQuestion()
            shouldShowAd()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun showInstructions() {
        listener.showFragmentInstructions(listener, getString(R.string.moneda))
    }

    override fun showQuestion() {
        numQuestion++
        pregunta.text = preguntas[indexList[numQuestion]]
        if (numQuestion == preguntas.size - 1) {
            numQuestion = -1
        }
        linearPregunta.visibility = View.VISIBLE
        linearMoneda.visibility = View.GONE
    }

    override fun showCoin() {
        linearPregunta.visibility = View.GONE
        linearMoneda.visibility = View.VISIBLE
    }

    override fun resetCoin() {
        coin.isClickable = true
        coin.setImageResource(R.drawable.ic_moneda_neutro)
        coinResult.text = ""
        theQuestionWas.text = ""
    }

    override fun flipCoin(imageId: Int, result: String) {
        coin.animate().apply {
            duration = 1000
            rotationYBy(1800f)
            coin.isClickable = false
        }.withEndAction {
            againButton.visibility = View.VISIBLE
            coin.setImageResource(imageId)
            coinResult.text = result
            setQuestionIfTails(result)
        }.start()
    }

    private fun setQuestionIfTails(result: String) {
        if (result == "CRUZ") {
            theQuestionWas.text = mContext.getString(R.string.theQuestionWas, pregunta.text)
        }
    }

    private fun shouldShowAd() {
        if (numQuestion % 20 == 0) {
            listener.showAd()
        }
    }
}