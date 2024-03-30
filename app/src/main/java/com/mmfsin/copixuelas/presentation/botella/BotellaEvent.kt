package com.mmfsin.copixuelas.presentation.botella

import com.mmfsin.copixuelas.domain.models.BotellaSpins

sealed class BotellaEvent {
    class GetSpins(val data: BotellaSpins) : BotellaEvent()
    object SWW : BotellaEvent()
}