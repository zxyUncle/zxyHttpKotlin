package com.zxy.http

/**
 * Created by ZXY on 2019/9/10 14:01.
 * Class functions
 * *********************************************************
 * * 网路配置
 * *********************************************************
 */
class OkHttpConfig {
    companion object {
        //网路请求的配置
        var CODE_SUCC: Int = 0 //成功的Code
        var CODE_TOKEN_INVALID = 40401 //Token过期
        var access_token = 40401 //access_token
        var refresh_token = 40401 //refresh_token

        var HTTP_DEBUG_HOSTURL = "https://www.wanandroid.com/" //测试服务器地址
        var HTTP_RESEASE_HOSTURL = "https://www.wanandroid.com/" //线上服务器地址
        var HTTP_TAG = "zxy" //网路请求数据显示的Logcat 日志过滤TAG

    }

}