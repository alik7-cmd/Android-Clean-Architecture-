package com.techascent.cleanarchitecture2.domain.common

sealed class BaseResult<out T : Any, out U : Any>{
    data class Success<T : Any>(val data : T) : BaseResult<T, Nothing>()
    data class Error<U : Any> (val errorResponse : U) : BaseResult<Nothing, U>()

}


sealed class Resource<T>(val data: T? = null,
                         val message: String? = null){

    class Success<T>(data: T) : Resource<T>(data)

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class Error<T>(errorMessage: String, data: T? = null) : Resource<T>(data, errorMessage)

    // We'll just pass object of this Loading
    // class, just before making an api call
    class Loading<T> : Resource<T>()

}
