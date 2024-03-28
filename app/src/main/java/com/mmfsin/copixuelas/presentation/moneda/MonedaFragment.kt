package com.mmfsin.copixuelas.presentation.moneda

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getMonedaData
import com.mmfsin.copixuelas.databinding.FragmentMonedaBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CARA
import com.mmfsin.copixuelas.presentation.moneda.CoinResult.CRUZ
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonedaFragment : BaseFragment<FragmentMonedaBinding, MonedaViewModel>() {

    override val viewModel: MonedaViewModel by viewModels()

    private var data = listOf<String>()
    private var position = -1

    private lateinit var mContext: Context

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMonedaBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMonedaData()
    }

    override fun setUI() {
        setUpToolbar()
//        showInstructions()
        setAdViewBackground()
        setInitialData()
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_moneda_dark))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            tvTitle.text = getString(R.string.category_moneda)
            tvTitle.typeface = ResourcesCompat.getFont(mContext, R.font.moneda_font)
            ivInstructions.setOnClickListener { showInstructions() }
        }
    }

    private fun setInitialData() {
        position++
        binding.apply {
//            tvQuestion.text = data[position]
//            tvQuestionResult.text = data[position]
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


    private fun showInstructions() =
        activity?.let { InstructionsDialog(MONEDA).show(it.supportFragmentManager, "") }

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