package com.techascent.cleanarchitecture2.data.common

import com.techascent.cleanarchitecture2.SharedPrefs
import com.techascent.cleanarchitecture2.data.common.ResponseHeader
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(val prefs: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(ResponseHeader.X_KM_AUTH_TOKEN, prefs.getToken())
            .addHeader(ResponseHeader.X_KM_REFRESH_TOKEN, prefs.getToken())
            .build()
        return chain.proceed(newRequest)
    }
}