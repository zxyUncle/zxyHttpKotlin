package com.zxy.http

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragmentActivity
import com.zxy.http.entity.BaseBean
import com.zxy.http.interceptro.HeaderInterceptoer
import com.zxy.http.interceptro.HttpLoggerInterceptor
import com.zxy.http.tools.LogcatUitls
import com.zxy.http.tools.NetWorkListener
import com.zxy.http.utils.*
import com.zxy.httpnet.utils.NetWorkUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by ZXY on 2019/9/10 12:29.
 * Class functions
 * *********************************************************
 * * 网路请求封装
 * *********************************************************
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
open class OkHttpService {
    private lateinit var mContext: RxAppCompatActivity

    companion object {
        val INSTANCE: OkHttpService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpService()
        }
    }

    fun apiService(mContext: RxAppCompatActivity): OkHttpApi {
        this.mContext = mContext
        var hostUrl =
            if (BuildConfig.DEBUG) OkHttpConfig.HTTP_DEBUG_HOSTURL else OkHttpConfig.HTTP_RESEASE_HOSTURL
        return getService(hostUrl, OkHttpApi::class.java)
    }

    private fun <A> getService(baseUrl: String, service: Class<A>): A {
        val clien = OkHttpClient.Builder() //自定义请求头
            .addNetworkInterceptor(HeaderInterceptoer()) //请求体过滤
            .addInterceptor(HttpLoggerInterceptor(mContext))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .build()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl) //格式转换
            .addConverterFactory(GsonConverterFactory.create()) //正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或其他类型
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clien)
            .build()
        return retrofit.create(service)
    }

    /**
     * 显示Dialog 加载中动画的网路
     *
     * @param observable         Observable<T>
     * @param httpClickLenerlist HttpClickLenerlist<T>
     *     ,httpClickLenerlist: NetWorkListener<T>
     */
    @SuppressLint("CheckResult")
    open fun <T> callBack(
        observable: Observable<BaseBean<T>>,
        onSucc:(BaseBean<T>.() -> Unit) = {},
        onFail: (BaseBean<T>.() -> Unit) = {},
        onNetWorkError: (Throwable?.() -> Unit) = {}
    ) {
        if (!NetWorkUtils.isNetAvailable(mContext)) {
            onNetWorkError(null)
        } else {
            observable.subscribeOn(Schedulers.io())
                .compose(mContext.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ baseBean ->  //请求的next对象
                    LogcatUitls.printJson(OkHttpConfig.HTTP_TAG, Gson().toJson(baseBean))
                    if ((baseBean as BaseBean<*>).errorCode === OkHttpConfig.CODE_SUCC) {
                        onSucc(baseBean)
                    } else {
                        if ((baseBean as BaseBean<*>).errorCode === OkHttpConfig.CODE_TOKEN_INVALID) {
                            //todo Token过期，跳转到登录界面
                        } else {
                            onFail(baseBean)
                        }
                    }
                }, { throwable: Throwable ->  //error
                    //加载动画隐藏
                    LogcatUitls.printJson(OkHttpConfig.HTTP_TAG, Gson().toJson(throwable))
                    onNetWorkError(throwable)
                }, {

                })
        }
    }
}