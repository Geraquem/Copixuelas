package com.mmfsin.copixuelas.presentation.category

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getIntroPhrase
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToAVQP
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMaletin
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMoneda
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToQPrefieres
import com.mmfsin.copixuelas.presentation.warning.WarningDialog

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun setUI() {
        showWarningDialog()
        setAdViewBackground()
        binding.apply {
            tvPhrase.text = getIntroPhrase()
        }
    }

    override fun setListeners() {
        binding.apply {
            btnAvqp.setOnClickListener { findNavController().navigate(actionMainToAVQP()) }
            btnMoneda.setOnClickListener { findNavController().navigate(actionMainToMoneda()) }
            btnQprefieres.setOnClickListener { findNavController().navigate(actionMainToQPrefieres()) }
            btnMaletin.setOnClickListener { findNavController().navigate(actionMainToMaletin()) }
            btnMoreGames.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.mmfsinURL))))
            }
        }
    }

    private fun setAdViewBackground() =
        activity?.let { (it as MainActivity).setAdViewBackGroundColor(R.color.bg_category) }

    private fun showWarningDialog() =
        activity?.let { WarningDialog().show(it.supportFragmentManager, "") }
}