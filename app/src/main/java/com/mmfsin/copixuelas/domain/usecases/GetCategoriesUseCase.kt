package com.mmfsin.copixuelas.domain.usecases

import android.content.Context
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.models.Category
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    @ApplicationContext val context: Context,
) : BaseUseCaseNoParams<List<Category>>() {

    override suspend fun execute(): List<Category> {
        return emptyList()
    }
}