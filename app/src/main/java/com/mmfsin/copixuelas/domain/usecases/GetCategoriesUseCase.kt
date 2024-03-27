package com.mmfsin.copixuelas.domain.usecases

import android.content.Context
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    @ApplicationContext val context: Context,
) : BaseUseCaseNoParams<Unit>() {

    override suspend fun execute() {}
}