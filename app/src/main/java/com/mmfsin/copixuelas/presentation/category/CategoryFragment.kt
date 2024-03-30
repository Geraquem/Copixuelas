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
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getIntroPhrase
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.AVQP
import com.mmfsin.copixuelas.domain.models.CategoryType.BOTELLA
import com.mmfsin.copixuelas.domain.models.CategoryType.MALETIN
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.domain.models.CategoryType.QPREFIERES
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToAVQP
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToBotella
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMaletin
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToMoneda
import com.mmfsin.copixuelas.presentation.category.CategoryFragmentDirections.Companion.actionMainToQPrefieres
import com.mmfsin.copixuelas.presentation.instructions.InstructionsDialog
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
        findNavController().navigate(CategoryFragmentDirections.actionMainToMaletin())
    }

    override fun setUI() {
        binding.apply {
            llTop.visibility = View.GONE
            llButtons.visibility = View.GONE
        }
        setCategoriesData()
//        showWarningDialog()
        setBannerInvisible()
        setInitialAnimations()
        binding.tvPhrase.text = getIntroPhrase()
    }

    private fun setBannerInvisible() =
        (activity as MainActivity).bannerVisible(isVisible = false)

    private fun setCategoriesData() {
        binding.apply {
            btnMoneda.apply {
                image.setImageResource(R.drawable.category_moneda)
            }
            btnQprefieres.apply {
                image.setImageResource(R.drawable.category_qprefieres)
            }
            btnMaletin.apply {
                image.setImageResource(R.drawable.category_maletin)
            }
        }
    }

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
            btnAvqp.container.setOnClickListener { onCategoryClick(AVQP) }
            btnMoneda.container.setOnClickListener { onCategoryClick(MONEDA) }
            btnQprefieres.container.setOnClickListener { onCategoryClick(QPREFIERES) }
            btnBotella.container.setOnClickListener { onCategoryClick(BOTELLA) }
            btnMaletin.container.setOnClickListener { onCategoryClick(MALETIN) }
            btnMoreGames.container.setOnClickListener {
                val url = getString(R.string.mmfsinURL)
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
        setLongListeners()
    }

    private fun setLongListeners() {
        binding.apply {
            btnAvqp.container.setOnLongClickListener {
                onCategoryLongClick(AVQP)
                true
            }
            btnMoneda.container.setOnLongClickListener {
                onCategoryLongClick(MONEDA)
                true
            }
            btnQprefieres.container.setOnLongClickListener {
                onCategoryLongClick(QPREFIERES)
                true
            }
            btnBotella.container.setOnLongClickListener {
                onCategoryLongClick(BOTELLA)
                true
            }
            btnMaletin.container.setOnLongClickListener {
                onCategoryLongClick(MALETIN)
                true
            }
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
            BOTELLA -> actionMainToBotella()
            MALETIN -> actionMainToMaletin()
        }
        findNavController().navigate(action)
    }

    private fun onCategoryLongClick(type: CategoryType) {
        val dialog = InstructionsDialog(type)
        activity?.let { dialog.show(it.supportFragmentManager, "") }
    }

    private fun showWarningDialog() {
        activity?.let {
            val main = (activity as MainActivity)
            if (main.showWarningDialog) WarningDialog().show(it.supportFragmentManager, "")
            main.showWarningDialog = false
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}