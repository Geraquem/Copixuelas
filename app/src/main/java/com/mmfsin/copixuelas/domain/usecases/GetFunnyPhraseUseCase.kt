package com.mmfsin.copixuelas.domain.usecases

import com.mmfsin.copixuelas.base.BaseUseCaseNoParams
import com.mmfsin.copixuelas.domain.interfaces.IDataRepository
import javax.inject.Inject

class GetFunnyPhraseUseCase @Inject constructor(
    private val repository: IDataRepository
) : BaseUseCaseNoParams<String>() {

    override suspend fun execute(): String = repository.getFunnyPhrase()
}