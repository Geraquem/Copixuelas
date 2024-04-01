package com.mmfsin.copixuelas.presentation.maletin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragmentNoVM
import com.mmfsin.copixuelas.databinding.FragmentMaletinBinding
import com.mmfsin.copixuelas.domain.models.CategoryType.MALETIN
import com.mmfsin.copixuelas.domain.models.MaletinPhase
import com.mmfsin.copixuelas.domain.models.MaletinPhase.CLOSE_MALETINS
import com.mmfsin.copixuelas.domain.models.MaletinPhase.START_AGAIN
import com.mmfsin.copixuelas.domain.models.MaletinState
import com.mmfsin.copixuelas.domain.models.MaletinState.CLOSED
import com.mmfsin.copixuelas.domain.models.MaletinState.OPENED
import com.mmfsin.copixuelas.domain.models.MoneyPlace
import com.mmfsin.copixuelas.domain.models.MoneyPlace.BOTTOM
import com.mmfsin.copixuelas.domain.models.MoneyPlace.TOP
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaletinFragment : BaseFragmentNoVM<FragmentMaletinBinding>() {

    private lateinit var mContext: Context

    private var mode: MaletinPhase = CLOSE_MALETINS
    private var maletinTopType: MaletinState = OPENED
    private var maletinBottomType: MaletinState = OPENED
    private var moneyPlace: MoneyPlace = TOP
    private var times = 0

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMaletinBinding.inflate(inflater, container, false)

    override fun setUI() {
        setUpToolbar()
        showInstructions()
        setAdViewBackground()
        setInitialConfig()
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_maletin_dark))
            ivBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
            tvTitle.text = getString(R.string.category_maletin)
            tvTitle.typeface = ResourcesCompat.getFont(mContext, R.font.maletin_font)
            ivInstructions.setOnClickListener { showInstructions() }
        }
    }

    private fun setInitialConfig() {
        binding.apply {
            mode = CLOSE_MALETINS
            maletinTopType = OPENED
            maletinBottomType = OPENED
            maletinTop.isEnabled = true
            maletinBottom.isEnabled = true
            tvTopText.text = getString(R.string.maletin_title_1)
            maletinTop.setImageResource(R.drawable.ic_malet_empty)
            maletinBottom.setImageResource(R.drawable.ic_malet_empty)
            btnContinue.text = getString(R.string.maletin_close)
            btnContinue.visibility = View.INVISIBLE
        }
    }

    override fun setListeners() {
        binding.apply {
            maletinTop.setOnClickListener {
                when (maletinTopType) {
                    OPENED -> {
                        moneyPlace = TOP
                        maletinTop.setImageResource(R.drawable.ic_malet_money)
                        maletinBottom.setImageResource(R.drawable.ic_malet_empty)
                        btnContinue.visibility = View.VISIBLE
                    }

                    CLOSED -> {
                        maletinTop.isEnabled = false
                        maletinBottom.isEnabled = false
                        if (moneyPlace == TOP) {
                            maletinTop.setImageResource(R.drawable.ic_malet_money)
                            tvTopText.text = getString(R.string.maletin_finish_win)
                        } else {
                            maletinTop.setImageResource(R.drawable.ic_malet_empty)
                            tvTopText.text = getString(R.string.maletin_finish_loose)
                        }
                        mode = START_AGAIN
                        btnContinue.text = getString(R.string.maletin_again)
                        btnContinue.visibility = View.VISIBLE
                    }
                }
            }

            maletinBottom.setOnClickListener {
                when (maletinBottomType) {
                    OPENED -> {
                        moneyPlace = BOTTOM
                        maletinTop.setImageResource(R.drawable.ic_malet_empty)
                        maletinBottom.setImageResource(R.drawable.ic_malet_money)
                        btnContinue.visibility = View.VISIBLE
                    }

                    CLOSED -> {
                        maletinTop.isEnabled = false
                        maletinBottom.isEnabled = false
                        if (moneyPlace == BOTTOM) {
                            maletinBottom.setImageResource(R.drawable.ic_malet_money)
                            tvTopText.text = getString(R.string.maletin_finish_win)
                        } else {
                            maletinBottom.setImageResource(R.drawable.ic_malet_empty)
                            tvTopText.text = getString(R.string.maletin_finish_loose)
                        }
                        mode = START_AGAIN
                        btnContinue.text = getString(R.string.maletin_again)
                        btnContinue.visibility = View.VISIBLE
                    }
                }
            }

            btnContinue.setOnClickListener {
                when (mode) {
                    CLOSE_MALETINS -> {
                        tvTopText.text = getString(R.string.maletin_title_2)
                        maletinTop.setImageResource(R.drawable.ic_malet_closed)
                        maletinTopType = CLOSED
                        maletinBottom.setImageResource(R.drawable.ic_malet_closed)
                        maletinBottomType = CLOSED
                        btnContinue.visibility = View.INVISIBLE
                    }

                    START_AGAIN -> {
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