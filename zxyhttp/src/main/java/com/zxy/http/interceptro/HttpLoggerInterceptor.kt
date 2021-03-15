package com.zxy.http.interceptro

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragmentActivity
import com.zxy.http.OkHttpConfig
import com.zxy.http.tools.LogcatUitls
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by zsf on 2021/3/2 19:44
 * ******************************************
 * *
 * ******************************************
 */
class HttpLoggerInterceptor(mContext: RxAppCompatActivity) : Interceptor {
    private var  mContext: RxAppCompatActivity = mContext

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!request.url().toString().isEmpty()) {
            if ("POST" == request.method()) {
                val requestBody = request.body()
                var body = ""
                val buffer = Buffer()
                requestBody!!.writeTo(buffer)
                var charset = Charset.forName("UTF-8")
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType!!.charset(Charset.forName("UTF-8"))
                    body = buffer.readString(charset)
                }
                LogcatUitls.printPost(OkHttpConfig.HTTP_TAG, request.url().toString(), body)
            } else {
                LogcatUitls.printStirng(OkHttpConfig.HTTP_TAG, request.url().toString())
            }
        }
        return chain.proceed(request)
    }

}