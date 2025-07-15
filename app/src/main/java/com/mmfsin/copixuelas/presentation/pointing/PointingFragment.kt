package com.mmfsin.copixuelas.presentation.pointing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentPointingBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.SENALACION
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.utils.animateY
import com.mmfsin.copixuelas.utils.countDown
import com.mmfsin.copixuelas.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PointingFragment : BaseFragment<FragmentPointingBinding, PointingViewModel>() {

    override val viewModel: PointingViewModel by viewModels()
    private lateinit var mContext: Context

    private var instructions: InstructionsDialog? = null

    private var data = listOf<String>()
    private var position = 0

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPointingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPointingData()
    }

    override fun setUI() {
        changeStatusBarColor()
        setUpToolbar()
        showInstructions()
        setAdViewBackground()
        binding.apply {
            loading.root.visibility = View.VISIBLE
            tvText.text = getString(R.string.avqp_start)
        }
    }

    private fun changeStatusBarColor() {
        if (activity is MainActivity) (activity as MainActivity).changeStatusBarColor(
            color = R.color.bg_pointing_dark,
            darkIcons = false
        )
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            toolbar.setBackgroundColor(getColor(mContext, R.color.bg_pointing_dark))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            tvTitle.text = getString(R.string.category_pointing)
            tvTitle.typeface = ResourcesCompat.getFont(mContext, R.font.avqp_font)
            ivInstructions.setOnClickListener { showInstructions() }
        }
    }

    private fun showInstructions() {
        instructions = InstructionsDialog(SENALACION)
        activity?.let { instructions?.show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() {
        (activity as MainActivity).apply {
            setAdViewBackGroundColor(R.color.bg_pointing)
            bannerVisible()
        }
    }

    override fun setListeners() {
        binding.apply {
            rlText.setOnClickListener {
                hideText()
                position++
                if (position > data.size - 1) position = 0

                rlText.animateY(1000f, 200)
                countDown(300) {
                    rlText.animateY(0f, 200)
                    setTexts()
                }
                shouldShowAd()
            }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is PointingEvent.GetData -> {
                    data = event.data.shuffled()
                    binding.loading.root.visibility = View.GONE
                }

                is PointingEvent.SWW -> error()
            }
        }
    }

    private fun hideText() {
        binding.apply {
            tvText.isVisible = false
        }
    }

    private fun setTexts() {
        binding.apply {
            try {
                val actualData = data[position]
                tvText.text = actualData
                tvText.isVisible = true
            } catch (e: Exception) {
                error()
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
        if (position != 0 && position % 15 == 0) {
            activity?.let { (it as MainActivity).showInterstitial() }
        }
    }
}