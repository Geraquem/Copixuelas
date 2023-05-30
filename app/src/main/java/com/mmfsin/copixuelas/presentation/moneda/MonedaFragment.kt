package com.mmfsin.copixuelas.presentation.moneda

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getAVQPData
import com.mmfsin.copixuelas.databinding.FragmentMonedaBinding
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog

class MonedaFragment : BaseFragment<FragmentMonedaBinding>() {

    private var data = listOf<String>()

    private lateinit var mContext: Context

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMonedaBinding.inflate(inflater, container, false)

    override fun setUI() {
        showInstructions()
        setAdViewBackground()
        data = getAVQPData().shuffled()
//        binding.tvPhrase.text = getText(R.string.avqp_start)
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
//        val font = if (data[position].contains("REGLA")) R.font.texas else R.font.boogaloo
//        binding.tvPhrase.typeface = ResourcesCompat.getFont(mContext, font)
    }

    private fun showInstructions() {
        activity?.let { InstructionsDialog(R.string.inst_avqp).show(it.supportFragmentManager, "") }
    }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_avqp) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun shouldShowAd() {
//        if (position != 0 && position % 20 == 0) {
//            activity?.let { (it as MainActivity).showInterstitial() }
//        }
    }
}