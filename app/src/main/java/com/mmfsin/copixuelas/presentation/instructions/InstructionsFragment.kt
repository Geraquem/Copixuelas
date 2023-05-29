package com.mmfsin.copixuelas.presentation.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.domain.interfaces.ICommunication

class InstructionsFragment(private val listener: ICommunication, private val type: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_instrucciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        instructions.text = when (type) {
//            getString(R.string.averquepasa) -> getText(R.string.avqpInstructions)
//            getString(R.string.moneda) -> getText(R.string.monedaInstructions)
//            getString(R.string.maletin) -> getText(R.string.maletinInstructions)
//            else -> getString(R.string.app_name)
//        }
//
//        close.setOnClickListener { listener.closeFragment() }
    }
}