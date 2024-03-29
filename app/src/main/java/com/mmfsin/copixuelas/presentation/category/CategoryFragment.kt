package com.mmfsin.copixuelas.presentation.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getIntroPhrase
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToAVQP
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMaletin
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMoneda
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToQPrefieres
import com.mmfsin.copixuelas.presentation.category.interfaces.ICategoryListener
import com.mmfsin.copixuelas.presentation.warning.WarningDialog
import com.mmfsin.copixuelas.utils.animateY
import com.mmfsin.copixuelas.utils.countDown
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

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
        binding.apply {
            llTop.visibility = View.GONE
            llButtons.visibility = View.GONE
        }
//        showWarningDialog()
        setBannerInvisible()
        setInitialAnimations()
        binding.tvPhrase.text = getIntroPhrase()
    }

    private fun setBannerInvisible() =
        (activity as MainActivity).bannerVisible(isVisible = false)

    private fun setInitialAnimations() {
        binding.apply {
            val main = (activity as MainActivity)
            if (main.firstTime) {
                llTop.animateY(-500f, 10)
                llButtons.animateY(1500f, 10)
                countDown(500) {
                    llTop.visibility = View.VISIBLE
                    llTop.animateY(0f, 500)
                    llButtons.visibility = View.VISIBLE
                    llButtons.animateY(0f, 500)
                }
                main.firstTime = false
            } else {
                llTop.visibility = View.VISIBLE
                llButtons.visibility = View.VISIBLE
            }
        }
    }

    override fun setListeners() {
        binding.apply {
            btnAvqp.root.setOnClickListener { onCategoryClick(AVQP) }
            btnMoneda.root.setOnClickListener { onCategoryClick(MONEDA) }
            btnQprefieres.root.setOnClickListener { onCategoryClick(QPREFIERES) }
            btnBotella.root.setOnClickListener { }
            btnMaletin.root.setOnClickListener { onCategoryClick(MALETIN) }
            btnMoreGames.root.setOnClickListener { }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is CategoryEvent.GetCategories -> {}
                is CategoryEvent.SWW -> {}
            }
        }
    }

    private fun onCategoryClick(type: CategoryType) {
        val action = when (type) {
            AVQP -> actionMainToAVQP()
            MONEDA -> actionMainToMoneda()
            QPREFIERES -> actionMainToQPrefieres()
            MALETIN -> actionMainToMaletin()
        }
        findNavController().navigate(action)
    }

    private fun onCategoryLongClick(type: CategoryType) {

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