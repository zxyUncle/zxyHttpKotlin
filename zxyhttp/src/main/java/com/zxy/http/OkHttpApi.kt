package com.zxy.http


import com.zxy.http.entity.*
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*

/**
 * Created by ZXY on 2019/9/5 11:52.
 * Class functions
 * *********************************************************
 * * 网路接口
 * * 具体Retrfit注解查看：https://blog.csdn.net/fayangzhou/article/details/80859318
 * *********************************************************
 */
interface OkHttpApi {
    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getWXArticle(): Observable<BaseBean<ArticleData>>


}