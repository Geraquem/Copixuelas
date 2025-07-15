package com.mmfsin.copixuelas.presentation.pointing

sealed class PointingEvent {
    class GetData(val data: List<String>) : PointingEvent()
    data object SWW : PointingEvent()
}