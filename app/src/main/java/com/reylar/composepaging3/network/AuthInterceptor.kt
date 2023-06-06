package com.reylar.composepaging3.network

import com.reylar.composepaging3.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val url = request.url.newBuilder().addQueryParameter("api_key", BuildConfig.MOVIE_TOKEN).build()
        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}