package com.mmfsin.copixuelas.presentation.mimica

sealed class MimicaEvent {
    class GetData(val data: List<String>) : MimicaEvent()
    object SWW : MimicaEvent()
}