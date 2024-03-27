package com.mmfsin.copixuelas.base

abstract class BaseUseCase<params, T> {
    abstract suspend fun execute(params: params): T
}