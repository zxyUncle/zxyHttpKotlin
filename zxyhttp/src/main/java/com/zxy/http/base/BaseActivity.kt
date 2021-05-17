package com.zxy.http.base

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.zxy.http.OkHttpApi
import com.zxy.http.OkHttpService

/**
 * Created by zsf on 2021/3/4 19:28
 * ******************************************
 * *
 * ******************************************
 */
open class BaseActivity:RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}