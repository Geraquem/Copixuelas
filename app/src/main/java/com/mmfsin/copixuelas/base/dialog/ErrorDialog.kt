package com.mmfsin.copixuelas.base.dialog

import android.app.Dialog
import android.view.LayoutInflater
import com.mmfsin.copixuelas.base.BaseDialog
import com.mmfsin.copixuelas.databinding.DialogErrorBinding

class ErrorDialog(val action: () -> Unit?) : BaseDialog<DialogErrorBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogErrorBinding.inflate(inflater)

    override fun setCustomViewDialog(dialog: Dialog) = centerViewDialog(dialog)

    override fun setListeners() {
        binding.btnAccept.setOnClickListener {
            action()
            dismiss()
        }
    }
}