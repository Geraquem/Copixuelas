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
import com.mmfsin.copixuelas.domain.models.CategoryType.MIMICA
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.domain.models.CategoryType.QPREFIERES

class InstructionsDialog(private val type: CategoryType, private val title: Int? = null) :
    BaseDialog<DialogInstructionsBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogInstructionsBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        binding.apply {
            val instructionsTitle = title ?: run { R.string.ints_title }
            tvHowToPlay.text = getString(instructionsTitle)

            val text = when (type) {
                AVQP -> R.string.inst_avqp
                MONEDA -> R.string.inst_moneda
                QPREFIERES -> R.string.inst_qprefieres
                BOTELLA -> R.string.inst_botella
                MALETIN -> R.string.inst_maletin
                MIMICA -> R.string.inst_mimica
            }
            tvInstructions.text = getText(text)
        }
    }

    override fun setListeners() {
        binding.tvAccept.setOnClickListener { dismiss() }
    }
}