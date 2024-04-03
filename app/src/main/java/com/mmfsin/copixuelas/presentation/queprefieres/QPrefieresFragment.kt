package com.mmfsin.copixuelas.presentation.queprefieres

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentQueprefieresBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.QPREFIERES
import com.mmfsin.copixuelas.domain.models.QPrefieresData
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.utils.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QPrefieresFragment : BaseFragment<FragmentQueprefieresBinding, QPrefieresViewModel>() {

    override val viewModel: QPrefieresViewModel by viewModels()
    private lateinit var mContext: Context

    private var instructions: InstructionsDialog? = null

    private var data = listOf<QPrefieresData>()
    private var position = 0

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentQueprefieresBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getQPrefieresData()
    }

    override fun setUI() {
        setUpToolbar()
        showInstructions()
        setAdViewBackground()
        binding.loading.root.visibility = View.VISIBLE
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            handleStatusBarIcons(setDarkIcons = true)
            activity?.window?.statusBarColor =
                getColor(requireContext(), R.color.bg_qprefieres_button)
            toolbar.setBackgroundColor(getColor(mContext, R.color.bg_qprefieres_button))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            ivBack.setColorFilter(getColor(mContext, R.color.black))
            tvTitle.text = getString(R.string.category_qprefieres)
            tvTitle.setTextColor(getColor(mContext, R.color.black))
            tvTitle.typeface = ResourcesCompat.getFont(mContext, R.font.qprefieres_font)
            ivInstructions.setOnClickListener { showInstructions() }
            ivInstructions.setColorFilter(getColor(mContext, R.color.black))
        }
    }

    private fun handleStatusBarIcons(setDarkIcons: Boolean) {
        activity?.let {
            val window = it.window
            val controller = WindowInsetsControllerCompat(window, window.decorView)
            controller.isAppearanceLightStatusBars = setDarkIcons
        }
    }

    override fun setListeners() {
        binding.apply {
            btnNext.setOnClickListener {
                position++
                if (position > data.size - 1) position = 0
                setData()
            }

            btnPrev.setOnClickListener {
                position--
                if (position < 0) position = 0
                setData()
            }

            tvVivirEsDecidir.setOnClickListener {
                val url = getString(R.string.qr_vivir_es_decidir_url)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is QPrefieresEvent.GetData -> {
                    data = event.data.shuffled()
                    setData()
                    binding.loading.root.visibility = View.GONE
                }

                is QPrefieresEvent.SWW -> error()
            }
        }
    }

    private fun setData() {
        binding.apply {
            try {
                shouldShowAd()
                val actualData = data[position]
                actualData.title?.let { title ->
                    tvTopTitle.text = title
                    tvTopTitle.visibility = View.VISIBLE
                } ?: run { tvTopTitle.visibility = View.GONE }
                tvTop.text = actualData.top
                tvBottom.text = actualData.bottom
            } catch (e: Exception) {
                error()
            }
        }
    }

    private fun showInstructions() {
        instructions = InstructionsDialog(QPREFIERES)
        activity?.let { instructions?.show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() {
        (activity as MainActivity).apply {
            setAdViewBackGroundColor(R.color.bg_qprefieres)
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

    override fun onDestroy() {
        handleStatusBarIcons(setDarkIcons = false)
        super.onDestroy()
    }

    private fun shouldShowAd() {
        if (position != 0 && position % 20 == 0) {
            activity?.let { (it as MainActivity).showInterstitial() }
        }
    }
}