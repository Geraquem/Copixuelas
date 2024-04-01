package com.mmfsin.copixuelas.presentation.category

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.AVQP
import com.mmfsin.copixuelas.domain.models.CategoryType.BOTELLA
import com.mmfsin.copixuelas.domain.models.CategoryType.MALETIN
import com.mmfsin.copixuelas.domain.models.CategoryType.MIMICA
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.domain.models.CategoryType.QPREFIERES
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToAVQP
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToBotella
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMaletin
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMoneda
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToQPrefieres
import com.mmfsin.copixuelas.presentation.category.adapter.CategoryAdapter
import com.mmfsin.copixuelas.presentation.category.interfaces.ICategoryListener
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
import com.mmfsin.copixuelas.presentation.warning.WarningDialog
import com.mmfsin.copixuelas.utils.animateY
import com.mmfsin.copixuelas.utils.countDown
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
        viewModel.getFunnyPhrase()
    }

    override fun setUI() {
        binding.apply {
            llTop.visibility = View.GONE
            rvCategories.visibility = View.GONE
        }
        setBannerInvisible()
        setInitialAnimations()
    }

    private fun setBannerInvisible() =
        (activity as MainActivity).bannerVisible(isVisible = false)

    private fun setInitialAnimations() {
        binding.apply {
            val main = (activity as MainActivity)
            if (main.firstTime) {
                val warningDialog = WarningDialog {
                    animateViews()
                    main.firstTime = false
                }
                warningDialog.show(main.supportFragmentManager, "")
            } else {
                llTop.visibility = View.VISIBLE
                rvCategories.visibility = View.VISIBLE
            }
        }
    }

    private fun animateViews() {
        binding.apply {
            llTop.animateY(-500f, 10)
            rvCategories.animateY(1500f, 10)
            countDown(150) {
                llTop.visibility = View.VISIBLE
                llTop.animateY(0f, 500)
                rvCategories.visibility = View.VISIBLE
                rvCategories.animateY(0f, 500)
            }
        }
    }

    override fun setListeners() {
        binding.apply {
//            btnMoreGames.container.setOnClickListener {
//                val url = getString(R.string.mmfsinURL)
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
//            }
        }
    }

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is CategoryEvent.GetFunnyPhrase -> {
                    binding.tvPhrase.text = event.phrase
                    viewModel.getCategories()
                }

                is CategoryEvent.GetCategories -> setCategories(event.categories)
                is CategoryEvent.SWW -> {}
            }
        }
    }

    private fun setCategories(categories: List<Category>) {
        binding.rvCategories.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = CategoryAdapter(categories, this@CategoryFragment)
        }
    }

    override fun onCategoryClick(type: CategoryType) {
        val action = when (type) {
            AVQP -> actionMainToAVQP()
            MONEDA -> actionMainToMoneda()
            QPREFIERES -> actionMainToQPrefieres()
            BOTELLA -> actionMainToBotella()
            MALETIN -> actionMainToMaletin()
            MIMICA -> actionMainToMaletin()
        }
        findNavController().navigate(action)
    }

    override fun onCategoryLongClick(type: CategoryType) {
        val dialog = InstructionsDialog(type)
        activity?.let { dialog.show(it.supportFragmentManager, "") }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}