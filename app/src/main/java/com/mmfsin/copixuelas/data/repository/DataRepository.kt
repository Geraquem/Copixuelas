package com.mmfsin.copixuelas.data.repository

import com.mmfsin.copixuelas.data.local.getBbbddMonedaData
import com.mmfsin.copixuelas.data.local.getBbddAVQPData
import com.mmfsin.copixuelas.data.local.getBbddQPrefieresData
import com.mmfsin.copixuelas.domain.interfaces.IDataRepository
import com.mmfsin.copixuelas.domain.models.AvqpData
import javax.inject.Inject

class DataRepository @Inject constructor() : IDataRepository {
    override fun getAvqpData(): List<AvqpData> = getBbddAVQPData().shuffled()
    override fun getMonedaData(): List<String> = getBbbddMonedaData().shuffled()
    override fun getQPrefieresData(): List<String> = getBbddQPrefieresData().shuffled()
}