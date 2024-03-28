package com.mmfsin.copixuelas.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: ViewBinding? = null

    protected abstract val viewModel: VM

    @Suppress("UNCHECKED_CAST")
    protected val binding
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateView(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        setListeners()
        observe()
    }

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup?): VB

    open fun setUI() {}
    open fun setListeners() {}
    open fun observe() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}