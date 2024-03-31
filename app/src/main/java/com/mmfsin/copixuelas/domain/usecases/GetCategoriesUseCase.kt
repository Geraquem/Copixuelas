package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
) : BaseUseCaseNoParams<List<Category>>() {

    override suspend fun execute(): List<Category> {
        val categories = mutableListOf<Category>()
        categories.add(
            Category(
                type = CategoryType.AVQP,
                title = R.string.category_avqp,
                font = R.font.avqp_font,
                image = R.drawable.category_moneda
            )
        )
        categories.add(
            Category(
                type = CategoryType.MONEDA,
                title = R.string.category_moneda,
                font = R.font.moneda_font,
                image = R.drawable.category_moneda
            )
        )
        return categories
    }
}