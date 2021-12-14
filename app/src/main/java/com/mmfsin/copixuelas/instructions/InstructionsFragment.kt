package com.mmfsin.copixuelas.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import kotlinx.android.synthetic.main.fragment_avqp_instrucciones.*

class InstructionsFragment(private val listener: IFragmentCommunication, private val type: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_avqp_instrucciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type == "avqp") {
            //tal tal
        }

        close.setOnClickListener { listener.closeFragment() }
    }
}