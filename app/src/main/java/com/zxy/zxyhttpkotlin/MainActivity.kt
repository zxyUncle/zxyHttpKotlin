package com.zxy.zxyhttpkotlin

import android.os.Bundle
import com.zxy.http.base.BaseActivity
import com.zxy.http.OkHttpService
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zsf on 2021/3/4 19:27
 * ******************************************
 * *
 * ******************************************
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvNetWord.setOnClickListener {
            loginCode()
        }
    }

   private fun loginCode() {
        OkHttpService.INSTANCE.callBack(okHttpApi.getWXArticle(), {
            //todo 成功过-可省略
        }, {
            //todo 失败-可省略
        }, {
            //todo 网路错误-可省略
        })
    }
}