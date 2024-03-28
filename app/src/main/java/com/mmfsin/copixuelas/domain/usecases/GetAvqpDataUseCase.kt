package com.mmfsin.copixuelas.domain.usecases

import android.content.Context
import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.data.local.getAVQPData
import com.mmfsin.copixuelas.domain.models.AvqpData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetAvqpDataUseCase @Inject constructor(
    @ApplicationContext val context: Context,
) : BaseUseCaseNoParams<List<AvqpData>>() {

    override suspend fun execute(): List<AvqpData> = getAVQPData()//.shuffled()
}