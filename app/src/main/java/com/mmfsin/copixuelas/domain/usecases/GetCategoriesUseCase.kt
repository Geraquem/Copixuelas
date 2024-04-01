package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
) : BaseUseCaseNoParams<List<Category>>() {

    override suspend fun execute(): List<Category> {
        val categories = mutableListOf<Category>()
        categories.add(
            Category(
                type = AVQP,
                title = R.string.category_avqp,
                image = R.drawable.bg_white_box
            )
        )
        categories.add(
            Category(
                type = MONEDA,
                title = R.string.category_moneda,
                image = R.drawable.category_moneda
            )
        )
        categories.add(
            Category(
                type = QPREFIERES,
                title = R.string.category_qprefieres,
                image = R.drawable.category_qprefieres
            )
        )
        categories.add(
            Category(
                type = BOTELLA,
                title = R.string.category_botella,
                image = R.drawable.category_botella
            )
        )
        categories.add(
            Category(
                type = MALETIN,
                title = R.string.category_maletin,
                image = R.drawable.category_maletin
            )
        )
        categories.add(
            Category(
                type = MIMICA,
                title = R.string.category_mimica,
                image = R.drawable.category_mimic
            )
        )
        return categories
    }
}