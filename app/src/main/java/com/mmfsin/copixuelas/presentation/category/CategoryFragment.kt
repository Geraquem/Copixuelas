package com.mmfsin.copixuelas.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseFragment
import com.mmfsin.copixuelas.data.local.getIntroPhrase
import com.mmfsin.copixuelas.databinding.FragmentCategoryBinding
import com.mmfsin.copixuelas.presentation.MainActivity
import com.mmfsin.copixuelas.presentation.warning.WarningDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    override val viewModel: CategoryViewModel by viewModels()

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

    override fun observe() {
        viewModel.event.observe(this) { event ->
            when (event) {
                is CategoryEvent.GetCategories -> {
                }

                is CategoryEvent.SWW -> {}
            }
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