package com.zxy.http.tools

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * Created by zxy on 2020/4/2 0002 19:57
 * ******************************************
 * *  将键值对转换成 RequestBody
 * ******************************************
 */

object JsonUtil {
    fun bodyVararg(vararg args: String): RequestBody {
        val map = HashMap<String, String>()
        for (i in args.indices step 2) {
            map[args[i]] = args[i + 1]
        }
        val json = Gson().toJson(map)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    }
    fun bodyMap(map:Map<String, Any>): RequestBody {
        val json = Gson().toJson(map)
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
    }

}