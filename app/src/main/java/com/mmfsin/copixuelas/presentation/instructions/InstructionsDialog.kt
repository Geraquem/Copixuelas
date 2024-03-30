package com.mmfsin.copixuelas.presentation.instructions

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseDialog
import com.mmfsin.copixuelas.databinding.DialogInstructionsBinding
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.AVQP
import com.mmfsin.copixuelas.domain.models.CategoryType.BOTELLA
import com.mmfsin.copixuelas.domain.models.CategoryType.MALETIN
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.domain.models.CategoryType.QPREFIERES

class InstructionsDialog(private val type: CategoryType) : BaseDialog<DialogInstructionsBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogInstructionsBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        val text = when (type) {
            AVQP -> R.string.inst_avqp
            MONEDA -> R.string.inst_moneda
            QPREFIERES -> R.string.inst_qprefieres
            BOTELLA -> R.string.app_name
            MALETIN -> R.string.inst_maletin
        }
        binding.tvInstructions.text = getText(text)
    }

    override fun setListeners() {
        binding.tvAccept.setOnClickListener { dismiss() }
    }
}