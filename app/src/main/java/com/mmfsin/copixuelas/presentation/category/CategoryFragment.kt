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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override fun inflateView(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun setUI() {
        showWarningDialog()
        setAdViewBackground()
        binding.tvPhrase.text = getIntroPhrase()
    }

    private fun setAdViewBackground() =
        (activity as MainActivity).setAdViewBackGroundColor(R.color.bg_category)

    override fun setListeners() {
        binding.apply {
//            btnAvqp.setOnClickListener { findNavController().navigate(actionMainToAVQP()) }
//            btnMoneda.setOnClickListener { findNavController().navigate(actionMainToMoneda()) }
//            btnQprefieres.setOnClickListener { findNavController().navigate(actionMainToQPrefieres()) }
//            btnMaletin.setOnClickListener { findNavController().navigate(actionMainToMaletin()) }
//            btnMoreGames.setOnClickListener {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.mmfsinURL))))
//            }
        }
    }

    /*

    private fun setUpCards(cards: List<Card>) {
        binding.rvCards.apply {
            (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
            cardsAdapter = CardsAdapter(cards, this@CardsFragment)
            adapter = cardsAdapter
        }
        activity?.showFragmentDialog(WaitSelectDialog { actionOnCard(selectedCardId) })
    }
     */

    private fun showWarningDialog() {
        activity?.let {
            val main = (it as MainActivity)
            if (main.showWarningDialog) WarningDialog().show(it.supportFragmentManager, "")
            main.showWarningDialog = false
        }
    }
}