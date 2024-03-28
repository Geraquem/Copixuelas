package com.mmfsin.copixuelas.domain.interfaces

import com.mmfsin.copixuelas.domain.models.AvqpData

interface IDataRepository {
    fun getAvqpData(): List<AvqpData>
    fun getMonedaData(): List<String>
    fun getQPrefieresData(): List<String>
}