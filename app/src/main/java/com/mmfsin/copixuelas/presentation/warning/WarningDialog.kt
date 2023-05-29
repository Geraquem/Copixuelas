package com.mmfsin.copixuelas.presentation.warning

import android.view.LayoutInflater
import com.mmfsin.copixuelas.base.BaseDialog
import com.mmfsin.copixuelas.databinding.DialogWarningBinding

class WarningDialog : BaseDialog<DialogWarningBinding>() {

    override fun inflateView(inflater: LayoutInflater) = DialogWarningBinding.inflate(inflater)

    override fun setUI() {
        isCancelable = false
    }

    override fun setListeners() {
        binding.btnAccept.setOnClickListener { dismiss() }
    }
}