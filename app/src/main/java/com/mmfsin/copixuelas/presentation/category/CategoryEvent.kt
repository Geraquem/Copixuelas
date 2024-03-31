package com.mmfsin.copixuelas.presentation.category

import com.mmfsin.copixuelas.domain.models.Category

sealed class CategoryEvent {
    class GetCategories(val categories: List<Category>) : CategoryEvent()
    class GetFunnyPhrase(val phrase: String) : CategoryEvent()
    object SWW : CategoryEvent()
}