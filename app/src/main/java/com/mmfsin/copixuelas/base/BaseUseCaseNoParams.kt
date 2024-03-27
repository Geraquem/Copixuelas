package com.mmfsin.copixuelas.base

abstract class BaseUseCaseNoParams<T> {
    abstract suspend fun execute(): T
}