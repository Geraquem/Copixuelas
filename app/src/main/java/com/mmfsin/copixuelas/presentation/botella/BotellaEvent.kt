package com.mmfsin.copixuelas.presentation.botella

sealed class BotellaEvent {
    class GetSpins(val spins: Long) : BotellaEvent()
    object SWW : BotellaEvent()
}