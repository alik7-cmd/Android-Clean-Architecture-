package com.techascent.cleanarchitecture2.domain.common

sealed class BaseResult<out T : Any, out U : Any>{
    data class Success<T : Any>(val data : T) : BaseResult<T, Nothing>()
    data class Error<U : Any> (val errorResponse : U) : BaseResult<Nothing, U>()

}
