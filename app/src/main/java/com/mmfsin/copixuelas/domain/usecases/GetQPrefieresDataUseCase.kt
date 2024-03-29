package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.interfaces.IDataRepository
import com.mmfsin.copixuelas.domain.models.QPrefieresData
import javax.inject.Inject

class GetQPrefieresDataUseCase @Inject constructor(
    private val repository: IDataRepository
) : BaseUseCaseNoParams<List<QPrefieresData>>() {

    override suspend fun execute(): List<QPrefieresData> = repository.getQPrefieresData()
}