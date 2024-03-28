package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.interfaces.IDataRepository
import javax.inject.Inject

class GetMonedaDataUseCase @Inject constructor(
    private val repository: IDataRepository
) : BaseUseCaseNoParams<List<String>>() {

    override suspend fun execute(): List<String> = repository.getMonedaData()
}