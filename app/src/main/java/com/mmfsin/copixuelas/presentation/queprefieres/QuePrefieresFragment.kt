package com.mmfsin.copixuelas.presentation.queprefieres

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.base.BaseFragmentNoVM
import com.mmfsin.copixuelas.data.local.getQPrefieresData
import com.mmfsin.copixuelas.databinding.FragmentQueprefieresBinding
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog

class QuePrefieresFragment : BaseFragmentNoVM<FragmentQueprefieresBinding>() {

    private var data = listOf<String>()
    private var position = 0

    private lateinit var mContext: Context

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentQueprefieresBinding.inflate(inflater, container, false)

    override fun setUI() {
        showInstructions()
        setAdViewBackground()
        data = getQPrefieresData().shuffled()
        setData()
    }

    private fun setData() {
        val split = data[position].split("%OR%")
        binding.apply {
            tvTop.text = split[0]
            tvBottom.text = split[1]
        }
    }

    override fun setListeners() {
        binding.apply {
            btnInstructions.setOnClickListener { showInstructions() }
            btnNext.setOnClickListener {
                position++
                if (position > data.size - 1) position = 0
                setData()
                shouldShowAd()
            }
            btnPrev.setOnClickListener {
                position--
                if (position < 0) position = 0
                setData()
                shouldShowAd()
            }
            tvVivirEsDecidir.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(getString(R.string.qr_vivir_es_decidir_url))
                    )
                )
            }
        }
    }


    private fun showInstructions() =
        activity?.let { InstructionsDialog(QPREFIERES).show(it.supportFragmentManager, "") }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_qprefieres) }

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