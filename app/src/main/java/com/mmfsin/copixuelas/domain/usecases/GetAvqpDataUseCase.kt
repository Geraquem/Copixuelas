package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.interfaces.IDataRepository
import com.mmfsin.copixuelas.domain.models.AvqpData
import javax.inject.Inject

class GetAvqpDataUseCase @Inject constructor(
    private val repository: IDataRepository
) : BaseUseCaseNoParams<List<AvqpData>>() {

    override suspend fun execute(): List<AvqpData> = repository.getAvqpData()
}