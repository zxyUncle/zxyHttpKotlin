package com.zxy.http.interceptro

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by zsf on 2021/3/2 19:45
 * ******************************************
 * *请求头过滤器
 * ******************************************
 */
class HeaderInterceptoer : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("token", "")
            .build()
        return chain.proceed(request)
    }
}