package com.mmfsin.copixuelas.queprefieres

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.instructions.IFragmentCommunication
import com.mmfsin.copixuelas.queprefieres.QuePrefieresData.getDilemmas
import com.mmfsin.copixuelas.removeLinksUnderline
import kotlinx.android.synthetic.main.fragment_quepreferirias.*

class QuePrefieresFragment(private val listener: IFragmentCommunication) : Fragment(),
    QuePrefieresView {

    private val presenter by lazy { QuePrefieresPresenter(this) }

    private val dilemmas = getDilemmas()
    private var indexList = ArrayList<Int>()
    private var numDilemma = 0

    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quepreferirias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showInstructions()
        indexList = presenter.setUpArray()
        presenter.setUpText()

        prevButton.setOnClickListener {
            numDilemma--
            presenter.setUpText()
        }

        nextButton.setOnClickListener {
            numDilemma++
            presenter.setUpText()
        }

        otherApp.movementMethod = LinkMovementMethod.getInstance()
        otherApp.removeLinksUnderline()
    }

    override fun setUpText() {
        presenter.checkButtons(numDilemma, dilemmas.size)
        val dilemma = dilemmas[indexList[numDilemma]]
        top.text = dilemma.split("%OR%")[0]
        bottom.text = dilemma.split("%OR%")[1]
    }

    override fun prevButton(isVisible: Boolean) {
        prevButton.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun nextButton(isVisible: Boolean) {
        nextButton.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun showInstructions() {
        listener.showFragmentInstructions(listener, "quepreferirias")
    }
}