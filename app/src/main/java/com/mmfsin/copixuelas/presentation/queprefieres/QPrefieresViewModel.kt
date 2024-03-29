package com.mmfsin.copixuelas.presentation.queprefieres

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetQPrefieresDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QPrefieresViewModel @Inject constructor(
    private val getQPrefieresDataUseCase: GetQPrefieresDataUseCase
) : BaseViewModel<QPrefieresEvent>() {

    fun getQPrefieresData() {
        executeUseCase(
            { getQPrefieresDataUseCase.execute() },
            { result -> _event.value = QPrefieresEvent.GetData(result) },
            { _event.value = QPrefieresEvent.SWW }
        )
    }
}