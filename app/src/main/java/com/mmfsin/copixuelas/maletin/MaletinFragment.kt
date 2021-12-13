package com.mmfsin.copixuelas.maletin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.instructions.IFragmentComunication
import com.mmfsin.copixuelas.instructions.InstructionsFragment

class MaletinFragment(private val listener: IFragmentComunication) : Fragment() {

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
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun showInstructions() {
        listener.showFragmentInstructions(InstructionsFragment(listener, "avqp"))
    }
}