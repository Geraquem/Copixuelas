package com.mmfsin.copixuelas.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun setUI() {
        binding.apply {

        }
    }

    override fun setListeners() {
        binding.apply {

        }
    }
}