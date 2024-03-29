package com.mmfsin.copixuelas.presentation.category

import com.mmfsin.copixuelas.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
) : BaseViewModel<CategoryEvent>() {

    fun getCategories() {
//        executeUseCase(
//            { getCategoriesUseCase.execute() },
//            { result -> _event.value = CategoryEvent.GetCategories(result) },
//            { _event.value = CategoryEvent.SWW }
//        )
    }
}