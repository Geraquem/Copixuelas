package com.mmfsin.copixuelas.presentation.botella

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentBotellaBinding
import com.mmfsin.copixuelas.domain.models.BotellaSpins
import com.mmfsin.copixuelas.domain.models.CategoryType.BOTELLA
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.utils.countDown
import com.mmfsin.copixuelas.utils.showErrorDialog
import com.mmfsin.copixuelas.utils.spinTheBottle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BotellaFragment : BaseFragment<FragmentBotellaBinding, BotellaViewModel>() {

    override val viewModel: BotellaViewModel by viewModels()
    private lateinit var mContext: Context

    private var instructions: InstructionsDialog? = null
    private var clicks = 0
    private var lastRotation = 0f

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentBotellaBinding.inflate(inflater, container, false)

    override fun setUI() {
        setUpToolbar()
//        showInstructions()
        setAdViewBackground()
        binding.btnSpin.visibility = View.VISIBLE
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_botella_dark))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            tvTitle.text = getString(R.string.category_botella)
            tvTitle.typeface = ResourcesCompat.getFont(mContext, R.font.avqp_font)
            ivInstructions.setOnClickListener { showInstructions() }
        }
    }

    private fun showInstructions() {
        instructions = InstructionsDialog(BOTELLA)
        activity?.let { instructions?.show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() {
        (activity as MainActivity).apply {
            setAdViewBackGroundColor(R.color.bg_botella)
            bannerVisible(false)
        }
    }

    override fun setListeners() {
        binding.apply {
            btnSpin.setOnClickListener {
                clicks++
                btnSpin.visibility = View.INVISIBLE
                viewModel.getSpins()
            }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is BotellaEvent.GetSpins -> spinTheBottle(event.data)
                is BotellaEvent.SWW -> error()
            }
        }
    }

    private fun spinTheBottle(data: BotellaSpins) {
        binding.apply {
            ivBottle.spinTheBottle(data.spins, data.duration, lastRotation) {
                countDown(300) { btnSpin.visibility = View.VISIBLE }
                shouldShowAd()
                lastRotation = data.spins
            }
        }
    }

    private fun error() {
        instructions?.dismiss()
        activity?.showErrorDialog { activity?.onBackPressedDispatcher?.onBackPressed() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun shouldShowAd() {
        if (clicks != 0 && clicks % 10 == 0) {
            (activity as MainActivity).showInterstitial()
        }
    }
}