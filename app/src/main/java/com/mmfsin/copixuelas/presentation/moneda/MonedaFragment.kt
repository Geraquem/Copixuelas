package com.mmfsin.copixuelas.presentation.moneda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getMonedaData
import com.mmfsin.copixuelas.databinding.FragmentMonedaBinding
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CARA
import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CRUZ

class MonedaFragment : BaseFragment<FragmentMonedaBinding>() {

    private var data = listOf<String>()
    private var position = -1

    private lateinit var mContext: Context

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMonedaBinding.inflate(inflater, container, false)

    override fun setUI() {
        showInstructions()
        setAdViewBackground()
        data = getMonedaData().shuffled()
        setInitialData()
    }

    private fun setInitialData() {
        position++
        binding.apply {
            tvQuestion.text = data[position]
            tvQuestionResult.text = data[position]
            tvSpin.visibility = View.VISIBLE
            ivCoin.isClickable = true
            ivCoin.setImageResource(R.drawable.ic_moneda_neutro)
            tvCoinResult.visibility = View.INVISIBLE
            llResult.visibility = View.INVISIBLE
            btnReplay.visibility = View.INVISIBLE
            clPhaseOne.visibility = View.VISIBLE
            clPhaseTwo.visibility = View.GONE
        }
    }

    override fun setListeners() {
        binding.apply {
            btnInstructions.setOnClickListener { showInstructions() }

            btnContinue.setOnClickListener {
                clPhaseOne.visibility = View.GONE
                clPhaseTwo.visibility = View.VISIBLE
                tvCoinResult.visibility = View.INVISIBLE
                llResult.visibility = View.INVISIBLE
            }

            ivCoin.setOnClickListener {
                tvSpin.visibility = View.INVISIBLE
                ivCoin.isClickable = false
                flipCoin(getCoinResult())
            }

            btnReplay.setOnClickListener {
                shouldShowAd()
                setInitialData()
            }
        }
    }

    private fun flipCoin(result: CoinResult) {
        binding.apply {
            ivCoin.animate().apply {
                duration = 1000
                rotationYBy(1800f)
            }.withEndAction {
                when (result) {
                    CARA -> {
                        ivCoin.setImageResource(R.drawable.ic_moneda_cara)
                        tvCoinResult.text = getString(R.string.moneda_cara)
                    }
                    CRUZ -> {
                        ivCoin.setImageResource(R.drawable.ic_moneda_cruz)
                        tvCoinResult.text = getString(R.string.moneda_cruz)
                        llResult.visibility = View.VISIBLE
                    }
                }
                tvCoinResult.visibility = View.VISIBLE
                btnReplay.visibility = View.VISIBLE
            }.start()
        }
    }

    private fun showInstructions() {
        activity?.let {
            InstructionsDialog(R.string.inst_moneda).show(it.supportFragmentManager, "")
        }
    }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_moneda) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun shouldShowAd() {
        if (position != 0 && position % 20 == 0) {
            activity?.let { (it as MainActivity).showInterstitial() }
        }
    }
}