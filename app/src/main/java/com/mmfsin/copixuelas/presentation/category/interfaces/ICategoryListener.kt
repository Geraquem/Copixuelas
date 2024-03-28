package com.mmfsin.copixuelas.presentation.category.interfaces

import com.mmfsin.copixuelas.domain.models.CategoryType

interface ICategoryListener {
    fun onCategoryClick(type: CategoryType)
    fun onCategoryLongClick(type: CategoryType)
}