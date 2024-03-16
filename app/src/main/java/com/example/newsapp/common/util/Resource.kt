package com.example.newsapp.common.util

sealed class Resource<T>(val data: List<T?> = emptyList(), val message: String? = null){
    class Success<T>(data: List<T?>): Resource<T>(data)
    class Error<T>(data:List<T?> = emptyList(), message: String): Resource<T>(data, message)
    class Loading<T>(data: List<T?> = emptyList()): Resource<T>(data)
}
