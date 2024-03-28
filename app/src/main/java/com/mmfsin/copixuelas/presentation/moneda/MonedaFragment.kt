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
import com.mmfsin.copixuelas.databinding.FragmentMonedaBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.domain.models.CoinResult
import com.mmfsin.copixuelas.domain.models.CoinResult.CARA
import com.mmfsin.copixuelas.domain.models.CoinResult.CRUZ
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.presentation.moneda.dialogs.MonedaResultDialog
import com.mmfsin.copixuelas.utils.countDown
import com.mmfsin.copixuelas.utils.flip
import com.mmfsin.copixuelas.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonedaFragment : BaseFragment<FragmentMonedaBinding, MonedaViewModel>() {

    override val viewModel: MonedaViewModel by viewModels()
    private lateinit var mContext: Context

    private var instructions: InstructionsDialog? = null

    private var data = listOf<String>()
    private var position = 0
    private var question: String? = null

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
        binding.loading.root.visibility = View.VISIBLE
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

    override fun setListeners() {
        binding.apply {
            btnContinue.setOnClickListener {
                clPhaseOne.visibility = View.GONE
                clPhaseTwo.visibility = View.VISIBLE
            }

            ivCoin.setOnClickListener {
                ivCoin.isClickable = false
                viewModel.flipCoin()
            }

            btnReplay.setOnClickListener { nextQuestion() }
        }
    }

    private fun nextQuestion() {
        shouldShowAd()
        position++
        if (position > data.size - 1) position = 0
        setInitialData()
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is MonedaEvent.GetData -> {
                    data = event.data.shuffled()
                    setInitialData()
                    binding.loading.root.visibility = View.GONE
                }

                is MonedaEvent.FlipCoin -> flipCoin(event.result)
                is MonedaEvent.SWW -> error()
            }
        }
    }

    private fun setInitialData() {
        binding.apply {
            try {
                val actualQuestion = data[position]
                tvQuestion.text = actualQuestion
                question = actualQuestion
                ivCoin.isClickable = true
                ivCoin.setImageResource(R.drawable.ic_moneda_neutro)
                tvSpin.text = getString(R.string.moneda_spin)
                btnReplay.visibility = View.INVISIBLE
                clPhaseOne.visibility = View.VISIBLE
                clPhaseTwo.visibility = View.GONE
            } catch (e: Exception) {
                error()
            }
        }
    }

    private fun flipCoin(result: CoinResult) {
        binding.apply {
            tvSpin.visibility = View.INVISIBLE
            shadow.flip { /** do nothing */ }
            ivCoin.flip {
                when (result) {
                    CARA -> {
                        ivCoin.setImageResource(R.drawable.ic_moneda_cara)
                        tvSpin.text = getString(R.string.moneda_cara)
                    }

                    CRUZ -> {
                        ivCoin.setImageResource(R.drawable.ic_moneda_cruz)
                        tvSpin.text = getString(R.string.moneda_cruz)
                    }
                }
                tvSpin.visibility = View.VISIBLE
                checkIfShowDialog(result)
            }
        }
    }

    private fun checkIfShowDialog(result: CoinResult) {
        when (result) {
            CRUZ -> {
                question?.let { question ->
                    countDown(200) {
                        val dialog = MonedaResultDialog(question) { nextQuestion() }
                        activity?.let { dialog.show(it.supportFragmentManager, "") }
                    }
                } ?: run { error() }
            }

            CARA -> binding.btnReplay.visibility = View.VISIBLE
        }
    }

    private fun showInstructions() {
        instructions = InstructionsDialog(MONEDA)
        activity?.let { instructions?.show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_moneda) }

    private fun error() {
        instructions?.dismiss()
        activity?.showErrorDialog { activity?.onBackPressedDispatcher?.onBackPressed() }
    }

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