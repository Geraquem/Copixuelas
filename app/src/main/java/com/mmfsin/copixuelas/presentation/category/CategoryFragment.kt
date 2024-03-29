package com.mmfsin.copixuelas.presentation.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getIntroPhrase
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToAVQP
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMaletin
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMoneda
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToQPrefieres
import com.mmfsin.copixuelas.presentation.category.interfaces.ICategoryListener
import com.mmfsin.copixuelas.presentation.warning.WarningDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>(),
    ICategoryListener {

    override val viewModel: CategoryViewModel by viewModels()
    private lateinit var mContext: Context

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategories()

        /** delete */
//        findNavController().navigate(CategoryFragmentDirections.actionMainToQPrefieres())
    }

    override fun setUI() {
//        showWarningDialog()
        setBannerInvisible()
        binding.tvPhrase.text = getIntroPhrase()
    }

    private fun setBannerInvisible() =
        (activity as MainActivity).bannerVisible(isVisible = false)

    override fun setListeners() {
        binding.apply {}
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is CategoryEvent.GetCategories -> {}
                is CategoryEvent.SWW -> {}
            }
        }
    }

    override fun onCategoryClick(type: CategoryType) {
        val action = when (type) {
            CategoryType.AVQP -> actionMainToAVQP()
            CategoryType.MONEDA -> actionMainToMoneda()
            CategoryType.QPREFIERES -> actionMainToQPrefieres()
            CategoryType.MALETIN -> actionMainToMaletin()
        }
        findNavController().navigate(action)
    }

    override fun onCategoryLongClick(type: CategoryType) {

    }

    private fun showWarningDialog() {
        activity?.let {
            val main = (it as MainActivity)
            if (main.showWarningDialog) WarningDialog().show(it.supportFragmentManager, "")
            main.showWarningDialog = false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}