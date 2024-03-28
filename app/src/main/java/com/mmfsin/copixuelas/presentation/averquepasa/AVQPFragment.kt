package com.mmfsin.copixuelas.presentation.averquepasa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getFont
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentAvqpBinding
import com.mmfsin.copixuelas.domain.models.AvqpData
import com.mmfsin.copixuelas.domain.models.CategoryType.AVQP
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AVQPFragment : BaseFragment<FragmentAvqpBinding, AVQPViewModel>() {

    override val viewModel: AVQPViewModel by viewModels()

    private var instructions: InstructionsDialog? = null

    private var data = listOf<AvqpData>()
    private var position = -1

    private lateinit var mContext: Context

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAvqpBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAvqpData()
    }

    override fun setUI() {
        setUpToolbar()
        showInstructions()
        setAdViewBackground()
        binding.apply {
            loading.root.visibility = View.VISIBLE
            tvTextOne.text = getString(R.string.avqp_start)
            tvTextTwo.visibility = View.GONE
            tvTextThree.visibility = View.GONE
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_avqp_dark))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            tvTitle.text = getString(R.string.category_avqp)
            tvTitle.typeface = getFont(mContext, R.font.avqp_font)
            ivInstructions.setOnClickListener { showInstructions() }
        }
    }

    private fun showInstructions() {
        instructions = InstructionsDialog(AVQP)
        activity?.let { instructions?.show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_avqp) }

    override fun setListeners() {
        binding.apply {
            llRule.setOnClickListener {
                hideTexts()
                position++
                if (position > data.size - 1) position = 1
                llRule.animate().apply {
                    duration = 200
                    rotationYBy(180f)
                }.withEndAction {
                    setTexts()
                }.start()
                scaleTexts()
                shouldShowAd()
            }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is AVQPEvent.GetData -> {
                    data = event.data.take(3)
                    binding.loading.root.visibility = View.GONE
                }

                is AVQPEvent.SWW -> error()
            }
        }
    }

    private fun hideTexts() {
        binding.apply {
            tvTextOne.isVisible = false
            tvTextTwo.isVisible = false
            tvTextThree.isVisible = false
        }
    }

    private fun scaleTexts() {
        binding.apply {
            tvTextOne.scaleX = if (position % 2 == 0) -1f else 1f
            tvTextTwo.scaleX = if (position % 2 == 0) -1f else 1f
            tvTextThree.scaleX = if (position % 2 == 0) -1f else 1f
        }
    }

    private fun setTexts() {
        binding.apply {
            try {
                val actualData = data[position]

                /** first */
                actualData.first?.let { firstText ->
                    tvTextOne.text = firstText
                    tvTextOne.visibility = View.VISIBLE
                } ?: run {
                    tvTextOne.visibility = View.GONE
                }

                /** second */
                tvTextTwo.text = actualData.second
                tvTextTwo.visibility = View.VISIBLE

                /** third */
                actualData.third?.let { thirdText ->
                    tvTextThree.text = thirdText
                    tvTextThree.visibility = View.VISIBLE
                } ?: run {
                    tvTextThree.visibility = View.GONE
                }
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
        if (position != 0 && position % 20 == 0) {
            (activity as MainActivity).showInterstitial()
        }
    }
}