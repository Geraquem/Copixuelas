package com.mmfsin.copixuelas.presentation.averquepasa

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.core.content.res.ResourcesCompat.getFont
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragmentNoVM
import com.mmfsin.copixuelas.data.local.getAVQPData
import com.mmfsin.copixuelas.databinding.FragmentAvqpBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog


class AVQPFragment : BaseFragmentNoVM<FragmentAvqpBinding>() {

    private var data = listOf<String>()
    private var position = -1

    private lateinit var mContext: Context

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAvqpBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = getAVQPData().shuffled()
    }

    override fun setUI() {
        setUpToolbar()
        showInstructions()
        setAdViewBackground()
        binding.apply {
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

    override fun setListeners() {
        binding.apply {
//            btnInstructions.setOnClickListener { showInstructions() }
//            tvPhrase.setOnClickListener {
//                position++
//                if (position > data.size - 1) position = 0
//                tvPhrase.text = data[position]
//                checkIfRule()
//                shouldShowAd()
//            }
        }
    }

    private fun checkIfRule() {
        val font =
            if (data[position].contains("REGLA")) R.font.qprefieres_font else R.font.avqp_font
//        binding.tvPhrase.typeface = ResourcesCompat.getFont(mContext, font)
    }

    private fun showInstructions() =
        activity?.let { InstructionsDialog(AVQP).show(it.supportFragmentManager, "") }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_avqp) }

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