package com.mmfsin.copixuelas.presentation.avqp

import com.mmfsin.copixuelas.domain.models.AvqpData

sealed class AVQPEvent {
    class GetData(val data: List<AvqpData>) : AVQPEvent()
    object SWW : AVQPEvent()
}