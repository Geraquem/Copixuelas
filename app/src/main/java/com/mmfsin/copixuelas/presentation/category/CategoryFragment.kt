package com.mmfsin.copixuelas.presentation.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getIntroPhrase
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToAVQP
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMaletin
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMoneda
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToQPrefieres
import com.mmfsin.copixuelas.presentation.category.adapter.CategoryAdapter
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
//        findNavController().navigate(CategoryFragmentDirections.actionMainToMoneda())
    }

    override fun setUI() {
//        showWarningDialog()
        setAdViewBackground()
        binding.tvPhrase.text = getIntroPhrase()
    }

    private fun setAdViewBackground() =
        (activity as MainActivity).setAdViewBackGroundColor(R.color.bg_category)

    override fun setListeners() {
        binding.apply {}
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is CategoryEvent.GetCategories -> setUpCategories(event.categories)
                is CategoryEvent.SWW -> {}
            }
        }
    }

    private fun setUpCategories(categories: List<Category>) {
        binding.rvCategories.apply {
            (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
            adapter = CategoryAdapter(categories, this@CategoryFragment)
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