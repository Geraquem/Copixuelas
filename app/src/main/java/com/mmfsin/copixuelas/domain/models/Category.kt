package com.mmfsin.copixuelas.domain.models

data class Category(
    val type: CategoryType,
    val title: Int,
    val image: Int,
    val order: Int
)