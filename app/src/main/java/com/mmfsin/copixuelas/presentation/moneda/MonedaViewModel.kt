package com.mmfsin.copixuelas.presentation.moneda

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.FlipCoinUseCase
import com.mmfsin.copixuelas.domain.usecases.GetMonedaDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MonedaViewModel @Inject constructor(
    private val getMonedaDataUseCase: GetMonedaDataUseCase,
    private val flipCoinUseCase: FlipCoinUseCase
) : BaseViewModel<MonedaEvent>() {

    fun getMonedaData() {
        executeUseCase(
            { getMonedaDataUseCase.execute() },
            { result -> _event.value = MonedaEvent.GetData(result) },
            { _event.value = MonedaEvent.SWW }
        )
    }

    fun flipCoin() {
        executeUseCase(
            { flipCoinUseCase.execute() },
            { result -> _event.value = MonedaEvent.FlipCoin(result) },
            { _event.value = MonedaEvent.SWW }
        )
    }
}