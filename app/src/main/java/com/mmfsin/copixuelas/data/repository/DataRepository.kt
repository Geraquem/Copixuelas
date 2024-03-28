package com.mmfsin.copixuelas.data.repository

import com.mmfsin.copixuelas.data.local.getAVQPData
import com.mmfsin.copixuelas.domain.interfaces.IDataRepository
import com.mmfsin.copixuelas.domain.models.AvqpData
import javax.inject.Inject

class DataRepository @Inject constructor() : IDataRepository {
    override fun getAvqpData(): List<AvqpData> = getAVQPData().shuffled()
    override fun getMonedaData(): List<String> = getMonedaData().shuffled()
    override fun getQPrefieresData(): List<String> = getQPrefieresData().shuffled()
}