package com.mmfsin.copixuelas.presentation.moneda.dialogs

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.copixuelas.base.BaseDialog
import com.mmfsin.copixuelas.databinding.DialogMonedaResultBinding

class MonedaResultDialog(
    private val result: String,
    private val anotherQuestion: () -> Unit
) : BaseDialog<DialogMonedaResultBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogMonedaResultBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = bottomViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
        binding.tvQuestionResult.text = result
    }

    override fun setListeners() {
        binding.btnOtherQuestion.setOnClickListener {
            anotherQuestion()
            dismiss()
        }
    }
}