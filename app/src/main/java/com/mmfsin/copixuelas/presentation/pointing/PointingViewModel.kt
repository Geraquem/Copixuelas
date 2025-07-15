package com.mmfsin.copixuelas.presentation.pointing

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetPointingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PointingViewModel @Inject constructor(
    private val getPointingDataUseCase: GetPointingDataUseCase
) : BaseViewModel<PointingEvent>() {

    fun getPointingData() {
        executeUseCase(
            { getPointingDataUseCase.execute() },
            { result -> _event.value = PointingEvent.GetData(result) },
            { _event.value = PointingEvent.SWW }
        )
    }
}