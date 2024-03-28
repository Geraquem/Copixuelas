package com.mmfsin.copixuelas.presentation.moneda

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetMonedaDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MonedaViewModel @Inject constructor(
    private val getMonedaDataUseCase: GetMonedaDataUseCase
) : BaseViewModel<MonedaEvent>() {

    fun getMonedaData() {
        executeUseCase(
            { getMonedaDataUseCase.execute() },
            { result -> _event.value = MonedaEvent.GetData(result) },
            { _event.value = MonedaEvent.SWW }
        )
    }
}