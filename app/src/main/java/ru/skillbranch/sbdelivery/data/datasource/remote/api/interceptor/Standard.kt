package ru.skillbranch.sbdelivery.data.datasource.remote.api.interceptor

import okhttp3.Request
import retrofit2.Invocation
import ru.skillbranch.sbdelivery.data.datasource.remote.api.annotation.Authenticated

fun Request.requestWithAuthentication(): Boolean = try {
    tag(Invocation::class.java)?.method()?.getAnnotation(Authenticated::class.java) != null
} catch (e: Exception) {
    false
}