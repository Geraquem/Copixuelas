package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType.AVQP
import com.mmfsin.copixuelas.domain.models.CategoryType.BOTELLA
import com.mmfsin.copixuelas.domain.models.CategoryType.MALETIN
import com.mmfsin.copixuelas.domain.models.CategoryType.MIMICA
import com.mmfsin.copixuelas.domain.models.CategoryType.MONEDA
import com.mmfsin.copixuelas.domain.models.CategoryType.QPREFIERES
import com.mmfsin.copixuelas.domain.models.CategoryType.SENALACION
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
) : BaseUseCaseNoParams<List<Category>>() {

    override suspend fun execute(): List<Category> {
        val categories = mutableListOf<Category>()
        categories.add(
            Category(
                type = AVQP,
                title = R.string.category_avqp,
                image = R.drawable.category_avqp,
                order = 1
            )
        )
        categories.add(
            Category(
                type = MONEDA,
                title = R.string.category_moneda,
                image = R.drawable.category_moneda,
                order = 2
            )
        )

        categories.add(
            Category(
                type = SENALACION,
                title = R.string.category_pointing,
                image = R.drawable.category_pointing,
                order = 3
            )
        )
        categories.add(
            Category(
                type = QPREFIERES,
                title = R.string.category_qprefieres,
                image = R.drawable.category_qprefieres,
                order = 4
            )
        )
        categories.add(
            Category(
                type = BOTELLA,
                title = R.string.category_botella,
                image = R.drawable.category_botella,
                order = 5
            )
        )
        categories.add(
            Category(
                type = MALETIN,
                title = R.string.category_maletin,
                image = R.drawable.category_maletin,
                order = 7
            )
        )
        categories.add(
            Category(
                type = MIMICA,
                title = R.string.category_mimica,
                image = R.drawable.category_mimic,
                order = 5
            )
        )
        return categories.sortedBy { it.order }
    }
}