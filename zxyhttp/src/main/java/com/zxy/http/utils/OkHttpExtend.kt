package com.zxy.http.utils

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.zxy.http.OkHttpService

/**
 * Created by zsf on 2021/5/17 18:01
 * ******************************************
 * *
 * ******************************************
 */
fun RxAppCompatActivity.okHttpApi() = OkHttpService.apiService(this)