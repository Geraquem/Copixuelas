package com.mmfsin.copixuelas.presentation.warning

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.copixuelas.base.BaseDialog
import com.mmfsin.copixuelas.databinding.DialogWarningBinding

class WarningDialog(val accept: () -> Unit) : BaseDialog<DialogWarningBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogWarningBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setUI() {
        isCancelable = false
    }

    override fun setListeners() {
        binding.btnAccept.setOnClickListener {
            accept()
            dismiss()
        }
    }
}