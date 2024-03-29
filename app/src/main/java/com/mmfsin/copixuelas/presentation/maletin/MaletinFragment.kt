package com.mmfsin.copixuelas.presentation.maletin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.base.BaseFragmentNoVM
import com.mmfsin.copixuelas.databinding.FragmentMaletinBinding
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.presentation.maletin.MaletinPhase.CLOSE
import com.mmfsin.copixuelas.presentation.maletin.MaletinPhase.REPLAY
import com.mmfsin.copixuelas.presentation.maletin.MaletinType.CLOSED
import com.mmfsin.copixuelas.presentation.maletin.MaletinType.OPENED
import com.mmfsin.copixuelas.presentation.maletin.MoneyPlace.BOTTOM
import com.mmfsin.copixuelas.presentation.maletin.MoneyPlace.TOP

class MaletinFragment : BaseFragmentNoVM<FragmentMaletinBinding>() {

    private lateinit var mContext: Context

    private var mode: MaletinPhase = CLOSE
    private var maletinTopType: MaletinType = OPENED
    private var maletinBottomType: MaletinType = OPENED
    private var moneyPlace: MoneyPlace = TOP
    private var times = 0

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMaletinBinding.inflate(inflater, container, false)

    override fun setUI() {
        showInstructions()
        setAdViewBackground()
        setInitialConfig()
    }

    private fun setInitialConfig() {
        binding.apply {
            mode = CLOSE
            maletinTopType = OPENED
            maletinBottomType = OPENED
            maletinTop.isEnabled = true
            maletinBottom.isEnabled = true
            tvTitle.text = getString(R.string.maletin_title_1)
            maletinTop.setImageResource(R.drawable.ic_maletin_one_opened)
            maletinBottom.setImageResource(R.drawable.ic_maletin_two_opened)
            btnContinue.text = getString(R.string.maletion_close)
            btnContinue.visibility = View.INVISIBLE
        }
    }

    override fun setListeners() {
        binding.apply {
            btnInstructions.setOnClickListener { showInstructions() }

            maletinTop.setOnClickListener {
                when (maletinTopType) {
                    OPENED -> {
                        moneyPlace = TOP
                        maletinTop.setImageResource(R.drawable.ic_maletin_one_money)
                        maletinBottom.setImageResource(R.drawable.ic_maletin_two_opened)
                        btnContinue.visibility = View.VISIBLE
                    }

                    CLOSED -> {
                        maletinTop.isEnabled = false
                        maletinBottom.isEnabled = false
                        if (moneyPlace == TOP) maletinTop.setImageResource(R.drawable.ic_maletin_one_money)
                        else maletinTop.setImageResource(R.drawable.ic_maletin_one_opened)
                        mode = REPLAY
                        btnContinue.text = getString(R.string.maletin_again)
                        btnContinue.visibility = View.VISIBLE
                    }
                }
            }

            maletinBottom.setOnClickListener {
                when (maletinBottomType) {
                    OPENED -> {
                        moneyPlace = BOTTOM
                        maletinTop.setImageResource(R.drawable.ic_maletin_one_opened)
                        maletinBottom.setImageResource(R.drawable.ic_maletin_two_money)
                        btnContinue.visibility = View.VISIBLE
                    }

                    CLOSED -> {
                        maletinTop.isEnabled = false
                        maletinBottom.isEnabled = false
                        if (moneyPlace == BOTTOM) maletinBottom.setImageResource(R.drawable.ic_maletin_two_money)
                        else maletinBottom.setImageResource(R.drawable.ic_maletin_two_opened)
                        mode = REPLAY
                        btnContinue.text = getString(R.string.maletin_again)
                        btnContinue.visibility = View.VISIBLE
                    }
                }
            }

            btnContinue.setOnClickListener {
                when (mode) {
                    CLOSE -> {
                        tvTitle.text = getString(R.string.maletin_title_2)
                        maletinTop.setImageResource(R.drawable.ic_maletin_one_closed)
                        maletinTopType = CLOSED
                        maletinBottom.setImageResource(R.drawable.ic_maletin_two_closed)
                        maletinBottomType = CLOSED
                        btnContinue.visibility = View.INVISIBLE
                    }

                    REPLAY -> {
                        times++
                        shouldShowAd()
                        setInitialConfig()
                    }
                }
            }
        }
    }


    private fun showInstructions() =
        activity?.let { InstructionsDialog(MALETIN).show(it.supportFragmentManager, "") }

    private fun setAdViewBackground() {
        (activity as MainActivity).apply {
            setAdViewBackGroundColor(R.color.bg_maletin)
            bannerVisible()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun shouldShowAd() {
        if (times != 0 && times % 8 == 0) {
            activity?.let { (it as MainActivity).showInterstitial() }
        }
    }
}