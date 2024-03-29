package com.mmfsin.copixuelas.presentation.queprefieres

import com.mmfsin.copixuelas.domain.models.QPrefieresData

sealed class QPrefieresEvent {
    class GetData(val data: List<QPrefieresData>) : QPrefieresEvent()
    object SWW : QPrefieresEvent()
}