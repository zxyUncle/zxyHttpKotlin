package com.zxy.zxyhttpkotlin

import android.os.Bundle
import androidx.core.content.edit
import com.zxy.http.base.BaseActivity
import com.zxy.http.OkHttpService
import com.zxy.http.utils.okHttpApi
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
        val sharedPreferences = getSharedPreferences("", MODE_PRIVATE);
        sharedPreferences.edit {

        }
    }

    private fun loginCode() {
        OkHttpService.request(reqeustApi = {
            okHttpApi().getWXArticle()
        }, onSucc = {
            //todo 成功过-可省略
        }, onFail = {
            //todo 失败-可省略
        }, onNetWorkError = {
            //todo 网路错误-可省略
        })
    }
}