package com.mmfsin.copixuelas.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import kotlinx.android.synthetic.main.fragment_instrucciones.*

class InstructionsFragment(private val listener: IFragmentCommunication, private val type: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_instrucciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        when (type) {
//            getString(R.string.averquepasa) -> System.out.println("787987")
//            getString(R.string.moneda) -> instructions.text = getString(R.string.monedaInstructions)
//            getString(R.string.queprefieresQuestion) -> System.out.println("787987")
//            getString(R.string.averquepasa) -> System.out.println("787987")
//        }

        close.setOnClickListener { listener.closeFragment() }
    }

    private fun avqp() {

    }
}