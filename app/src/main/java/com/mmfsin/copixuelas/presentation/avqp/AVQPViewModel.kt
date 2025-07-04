package com.mmfsin.copixuelas.presentation.avqp

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetAvqpDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AVQPViewModel @Inject constructor(
    private val getAvqpDataUseCase: GetAvqpDataUseCase
) : BaseViewModel<AVQPEvent>() {

    fun getAvqpData() {
        executeUseCase(
            { getAvqpDataUseCase.execute() },
            { result -> _event.value = AVQPEvent.GetData(result) },
            { _event.value = AVQPEvent.SWW }
        )
    }
}