package com.mmfsin.copixuelas.moneda

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.instructions.IFragmentCommunication
import kotlinx.android.synthetic.main.fragment_moneda.*

class MonedaFragment(private val listener: IFragmentCommunication) : Fragment(), MonedaView {

    private val presenter by lazy { MonedaPresenter(this) }

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

        coin.setOnClickListener {
            presenter.coinPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun showInstructions() {
        listener.showFragmentInstructions(listener, "moneda")
    }

    override fun flipCoin(imageId: Int, result: String) {
        coin.animate().apply {
            duration = 1000
            rotationYBy(1800f)
            coin.isClickable = false
        }.withEndAction {
            coin.setImageResource(imageId)
            coinResult.text = result
            coin.isClickable = true
        }.start()
    }

    override fun showMoneda() {
    }
}