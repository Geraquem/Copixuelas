package com.mmfsin.copixuelas.presentation.instructions

import android.view.LayoutInflater
import com.mmfsin.copixuelas.base.BaseDialog
import com.mmfsin.copixuelas.databinding.DialogInstructionsBinding

class InstructionsDialog(val text: Int) : BaseDialog<DialogInstructionsBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogInstructionsBinding.inflate(inflater)

    override fun setUI() {
        binding.tvInstructions.text = getText(text)
    }

    override fun setListeners() {
        binding.tvAccept.setOnClickListener { dismiss() }
    }
}