package com.mmfsin.copixuelas.instructions

import androidx.fragment.app.Fragment

interface IFragmentComunication {
    fun closeFragment()
    fun showFragmentInstructions(fragment: Fragment)
}