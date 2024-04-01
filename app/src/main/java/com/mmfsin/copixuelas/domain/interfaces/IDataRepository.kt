package com.mmfsin.copixuelas.domain.interfaces

import com.mmfsin.copixuelas.domain.models.AvqpData
import com.mmfsin.copixuelas.domain.models.QPrefieresData

interface IDataRepository {
    fun getFunnyPhrase(): String
    fun getAvqpData(): List<AvqpData>
    fun getMonedaData(): List<String>
    fun getQPrefieresData(): List<QPrefieresData>
    fun getMimicaData(): List<String>
}