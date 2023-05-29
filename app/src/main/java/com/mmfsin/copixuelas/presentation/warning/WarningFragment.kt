package com.mmfsin.copixuelas.presentation.warning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.domain.interfaces.ICommunication
import com.mmfsin.copixuelas.R
import kotlinx.android.synthetic.main.fragment_warning.*

class WarningFragment(private val listener: ICommunication) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_warning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        understood.setOnClickListener { listener.closeFragment() }
    }
}