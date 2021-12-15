package com.mmfsin.copixuelas.maletin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.instructions.IFragmentCommunication
import kotlinx.android.synthetic.main.fragment_maletin.*

class MaletinFragment(private val listener: IFragmentCommunication) : Fragment(), MaletinView {

    private val presenter by lazy { MaletinPresenter(this) }

    private val closed: String = "CLOSED"
    private val opened: String = "OPENED"
    private val money: String = "MONEY"

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

//        showInstructions()

        replayGame()

        maletinOne.setOnClickListener {
            maletinOne.setBackgroundResource(R.drawable.ic_sketch1639560105085)
            //            if(phase == 1){
//                maletinOne.tag = money
//                //setImagen
//                phase = 2
//            }
        }

        maletinTwo.setOnClickListener {
            presenter.setTag(maletinOne, opened)
        }
    }

    private fun showInstructions() {
        listener.showFragmentInstructions(listener, "maletin")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun replayGame() {
//        phase = 1
//        presenter.setTag(maletinOne, opened)
//        presenter.setTag(maletinTwo, opened)
//
//        maletinOne.setImageResource(R.drawable.ic_maletin_one_open)
//        maletinTwo.setImageResource(R.drawable.ic_maletin_two_open)
    }
}