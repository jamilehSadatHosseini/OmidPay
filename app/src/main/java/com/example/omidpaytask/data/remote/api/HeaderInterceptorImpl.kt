package com.example.omidpaytask.data.remote.api

import com.example.omidpaytask.data.util.Constants
import okhttp3.*
import javax.inject.Inject


class HeaderInterceptorImpl @Inject constructor() :Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url
        val url = originalHttpUrl.newBuilder().build()
        val requestBuilder: Request.Builder = original.newBuilder()
            .addHeader("Content-Type", "appliProduction/json")
            .url(url)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}