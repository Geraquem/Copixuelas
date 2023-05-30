package com.mmfsin.copixuelas.presentation.maletin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentMaletinBinding
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.presentation.maletin.MaletinPhase.CLOSE
import com.mmfsin.copixuelas.presentation.maletin.MaletinPhase.REPLAY
import com.mmfsin.copixuelas.presentation.maletin.MaletinType.CLOSED
import com.mmfsin.copixuelas.presentation.maletin.MaletinType.OPENED
import com.mmfsin.copixuelas.presentation.maletin.MoneyPlace.BOTTOM
import com.mmfsin.copixuelas.presentation.maletin.MoneyPlace.TOP

class MaletinFragment : BaseFragment<FragmentMaletinBinding>() {

    private lateinit var mContext: Context

    private var mode: MaletinPhase = CLOSE
    private var maletinTopType: MaletinType = OPENED
    private var maletinBottomType: MaletinType = OPENED
    private var moneyPlace: MoneyPlace = TOP

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

                    REPLAY -> setInitialConfig()
                }
            }
        }
    }

    private fun setClosedFlow() {
        binding.apply {
            when (moneyPlace) {
                TOP -> {

                    maletinBottom.setImageResource(R.drawable.ic_maletin_two_money)
                }

                BOTTOM -> {

                }
            }
        }
    }

    private fun showInstructions() {
        activity?.let {
            InstructionsDialog(R.string.inst_maletin).show(it.supportFragmentManager, "")
        }
    }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_maletin) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun shouldShowAd() {
//        if (position != 0 && position % 20 == 0) {
//            activity?.let { (it as MainActivity).showInterstitial() }
//        }
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        showInstructions()
//
//        replayGame()
//
////        info.setOnClickListener { showInstructions() }
////
////        maletinOne.setOnClickListener {
////            maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_money)
////            if (phase == 1) {
////                doPhaseOne("ONE")
////            } else if (phase == 2) {
////                maletinTwo.isClickable = false
////                if (maletinOne.tag == money) {
////                    maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_money)
////                } else {
////                    maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_opened)
////                }
////                retryButton.visibility = View.VISIBLE
////            }
////        }
////
////        maletinTwo.setOnClickListener {
////            if (phase == 1) {
////                doPhaseOne("TWO")
////
////            } else if (phase == 2) {
////                maletinOne.isClickable = false
////                if (maletinTwo.tag == money) {
////                    maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_money)
////                } else {
////                    maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_opened)
////                }
////                retryButton.visibility = View.VISIBLE
////            }
////        }
////
////        phaseTwoButton.setOnClickListener() { doPhaseTwo() }
////
////        retryButton.setOnClickListener { replayGame() }
//    }
//
//    private fun doPhaseOne(maletin: String) {
//        when (maletin) {
////            "ONE" -> {
////                maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_money)
////                maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_opened)
////
////                maletinOne.tag = money
////                maletinTwo.tag = empty
////
////            }
////            "TWO" -> {
////                maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_opened)
////                maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_money)
////
////                maletinOne.tag = empty
////                maletinTwo.tag = money
////            }
//        }
////        phaseTwoButton.visibility = View.VISIBLE
//    }
//
//    private fun doPhaseTwo() {
//        phase = 2
////
////        phaseTwoButton.visibility = View.GONE
////        phrase.text = mContext.getText(R.string.whereIsTheMoney)
////
////        maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_closed)
////        maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_closed)
//    }
//
//    override fun replayGame() {
//        phase = 1
//
////        phrase.text = mContext.getText(R.string.hideTheMoney)
////
////        phaseTwoButton.visibility = View.GONE
////        retryButton.visibility = View.GONE
////
////        maletinOne.tag = empty
////        maletinTwo.tag = empty
////        maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_opened)
////        maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_opened)
////
////        maletinOne.isClickable = true
////        maletinTwo.isClickable = true
//    }
//
//    private fun showInstructions() {
//        listener.showFragmentInstructions(listener, getString(R.string.category_maletin))
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext = context
//    }
}