package com.mmfsin.copixuelas.presentation.botella

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetBotellaSpinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BotellaViewModel @Inject constructor(
    private val getBotellaSpinUseCase: GetBotellaSpinUseCase
) : BaseViewModel<BotellaEvent>() {

    fun getSpins() {
        executeUseCase(
            { getBotellaSpinUseCase.execute() },
            { result -> _event.value = BotellaEvent.GetSpins(result) },
            { _event.value = BotellaEvent.SWW }
        )
    }
}