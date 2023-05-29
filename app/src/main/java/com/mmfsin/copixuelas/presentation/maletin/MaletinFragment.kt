package com.mmfsin.copixuelas.presentation.maletin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.domain.interfaces.ICommunication

class MaletinFragment(private val listener: ICommunication) : Fragment(), MaletinView {

    private val presenter by lazy { MaletinPresenter(this) }

    private val money: String = "MONEY"
    private val empty: String = "EMPTY"

    private var phase: Int = 1

    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maletin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showInstructions()

        replayGame()

//        info.setOnClickListener { showInstructions() }
//
//        maletinOne.setOnClickListener {
//            maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_money)
//            if (phase == 1) {
//                doPhaseOne("ONE")
//            } else if (phase == 2) {
//                maletinTwo.isClickable = false
//                if (maletinOne.tag == money) {
//                    maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_money)
//                } else {
//                    maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_opened)
//                }
//                retryButton.visibility = View.VISIBLE
//            }
//        }
//
//        maletinTwo.setOnClickListener {
//            if (phase == 1) {
//                doPhaseOne("TWO")
//
//            } else if (phase == 2) {
//                maletinOne.isClickable = false
//                if (maletinTwo.tag == money) {
//                    maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_money)
//                } else {
//                    maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_opened)
//                }
//                retryButton.visibility = View.VISIBLE
//            }
//        }
//
//        phaseTwoButton.setOnClickListener() { doPhaseTwo() }
//
//        retryButton.setOnClickListener { replayGame() }
    }

    private fun doPhaseOne(maletin: String) {
        when (maletin) {
//            "ONE" -> {
//                maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_money)
//                maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_opened)
//
//                maletinOne.tag = money
//                maletinTwo.tag = empty
//
//            }
//            "TWO" -> {
//                maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_opened)
//                maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_money)
//
//                maletinOne.tag = empty
//                maletinTwo.tag = money
//            }
        }
//        phaseTwoButton.visibility = View.VISIBLE
    }

    private fun doPhaseTwo() {
        phase = 2
//
//        phaseTwoButton.visibility = View.GONE
//        phrase.text = mContext.getText(R.string.whereIsTheMoney)
//
//        maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_closed)
//        maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_closed)
    }

    override fun replayGame() {
        phase = 1

//        phrase.text = mContext.getText(R.string.hideTheMoney)
//
//        phaseTwoButton.visibility = View.GONE
//        retryButton.visibility = View.GONE
//
//        maletinOne.tag = empty
//        maletinTwo.tag = empty
//        maletinOne.setBackgroundResource(R.drawable.ic_maletin_one_opened)
//        maletinTwo.setBackgroundResource(R.drawable.ic_maletin_two_opened)
//
//        maletinOne.isClickable = true
//        maletinTwo.isClickable = true
    }

    private fun showInstructions() {
        listener.showFragmentInstructions(listener, getString(R.string.maletin))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}