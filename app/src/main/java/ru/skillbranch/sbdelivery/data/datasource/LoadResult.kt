package ru.skillbranch.sbdelivery.data.datasource

sealed class  LoadResult<T>(val key: String){
    class Load<T>: LoadResult<T>("Load")
    data class Success<T>(val data: T): LoadResult<T>("Success")
    data class Error<T>(val error: String): LoadResult<T>("Error")
}
