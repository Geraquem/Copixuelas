package com.mmfsin.copixuelas.presentation.mimica

import com.mmfsin.copixuelas.base.BaseViewModel
import com.mmfsin.copixuelas.domain.usecases.GetMimicaDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MimicaViewModel @Inject constructor(
    private val getMimicaDataUseCase: GetMimicaDataUseCase,
) : BaseViewModel<MimicaEvent>() {

    fun getMimicData() {
        executeUseCase(
            { getMimicaDataUseCase.execute() },
            { result -> _event.value = MimicaEvent.GetData(result) },
            { _event.value = MimicaEvent.SWW }
        )
    }
}