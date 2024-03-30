package com.mmfsin.copixuelas.presentation.category

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetFunnyPhraseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getFunnyPhraseUseCase: GetFunnyPhraseUseCase
) : BaseViewModel<CategoryEvent>() {

    fun getFunnyPhrase() {
        executeUseCase(
            { getFunnyPhraseUseCase.execute() },
            { result -> _event.value = CategoryEvent.GetFunnyPhrase(result) },
            { _event.value = CategoryEvent.SWW }
        )
    }
}