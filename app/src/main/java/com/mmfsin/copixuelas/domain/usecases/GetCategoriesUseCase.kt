package com.mmfsin.copixuelas.domain.usecases

import android.content.Context
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.Category
import com.mmfsin.copixuelas.domain.models.CategoryType
import com.mmfsin.copixuelas.domain.models.CategoryType.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    @ApplicationContext val context: Context,
) : BaseUseCaseNoParams<List<Category>>() {

    override suspend fun execute(): List<Category> {
        val categories = mutableListOf<Category>()
        categories.apply {
            add(
                Category(
                    type = AVQP,
                    name = R.string.category_avqp,
                    description = R.string.category_avqp_description,
                    image = R.drawable.ic_crown
                )
            )
            add(
                Category(
                    type = MONEDA,
                    name = R.string.category_moneda,
                    description = R.string.category_moneda_description,
                    image = R.drawable.ic_crown
                )
            )
            add(
                Category(
                    type = QPREFIERES,
                    name = R.string.category_qprefieres,
                    description = R.string.category_qprefieres_description,
                    image = R.drawable.ic_crown
                )
            )
            add(
                Category(
                    type = MALETIN,
                    name = R.string.category_maletin,
                    description = R.string.category_maletin_description,
                    image = R.drawable.ic_crown
                )
            )
        }
        return categories
    }
}