package ru.skillbranch.sbdelivery.data.datasource.remote.api.interceptor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.skillbranch.sbdelivery.data.datasource.local.datastore.PreferenceManager
import javax.inject.Inject

class AppendTokenInterceptor @Inject constructor(
    private val preferenceManager: PreferenceManager
): Interceptor {

    companion object{
        const val AUTHORIZATION_HEADER_FIELD = "Authorization"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .apply {
                if (request.requestWithAuthentication()){
                    addHeader(AUTHORIZATION_HEADER_FIELD, runBlocking(Dispatchers.IO) { preferenceManager.accessTokenFlow.firstOrNull() }?.let { "Bearer $it"  } ?: "")
                }
            }
            .build()
        return chain.proceed(request)
    }
}