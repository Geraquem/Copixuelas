package com.mmfsin.copixuelas.presentation.mimica

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent.*
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentMimicaBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.MIMICA
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.utils.animateX
import com.mmfsin.copixuelas.utils.animateY
import com.mmfsin.copixuelas.utils.countDown
import com.mmfsin.copixuelas.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MimicaFragment : BaseFragment<FragmentMimicaBinding, MimicaViewModel>() {

    override val viewModel: MimicaViewModel by viewModels()
    private lateinit var mContext: Context

    private var instructions: InstructionsDialog? = null

    private var data = listOf<String>()
    private var position = 0
    private var actualMimic: String? = null

    private var originalRotation = 0f
    private var longPressDetected = false
    private var longPressRunnable: Runnable? = null

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMimicaBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMimicData()
    }

    override fun setUI() {
        setUpToolbar()
//        showInstructions()
        setAdViewBackground()
        binding.apply {
            loading.root.visibility = View.VISIBLE
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_mimic_dark))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            tvTitle.text = getString(R.string.category_mimica)
            tvTitle.typeface = ResourcesCompat.getFont(mContext, R.font.moneda_font)
            ivInstructions.setOnClickListener { showInstructions() }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setListeners() {
        binding.apply {
            rlCard.setOnTouchListener { _, event ->
                when (event.action) {
                    ACTION_DOWN -> {
                        originalRotation = rlCard.rotation
                        longPressRunnable = Runnable {
                            longPressDetected = true
                            rlCard.animateX(-2000f, 300)
                        }
                        rlCard.postDelayed(longPressRunnable, 250)
                        longPressDetected = false
                        true
                    }

                    ACTION_UP, ACTION_CANCEL -> {
                        rlCard.animateX(0f, 300)
                        rlCard.removeCallbacks(longPressRunnable)
                        true
                    }

                    else -> false
                }
            }

            btnStart.setOnClickListener { }

            btnNext.setOnClickListener {
                btnNext.isEnabled = false
                rlCard.animateX(2000f, 300)
                position++
                if (position > data.size - 1) position = 0
                setData()
                countDown(750) {
                    rlCard.animateX(0f, 300)
                    btnNext.isEnabled = true
                }
            }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is MimicaEvent.GetData -> {
                    data = event.data.shuffled()
                    setData()
                    binding.loading.root.visibility = View.GONE
                }

                is MimicaEvent.SWW -> error()
            }
        }
    }

    private fun setData() {
        binding.apply {
            try {
                actualMimic = data[position]
                tvMimic.text = actualMimic
//                shouldShowAd()
            } catch (e: Exception) {
                error()
            }
        }
    }

    private fun showInstructions() {
        instructions = InstructionsDialog(MIMICA)
        activity?.let { instructions?.show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() {
        (activity as MainActivity).apply {
            setAdViewBackGroundColor(R.color.bg_mimic)
            bannerVisible()
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
        if (position != 0 && position % 8 == 0) {
            activity?.let { (it as MainActivity).showInterstitial() }
        }
    }
}