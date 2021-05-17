@file:JvmName("LoadExtend")
@file:JvmMultifileClass
package com.zxy.http.tools

import android.content.Context

/**
 * Created by zsf on 2021/1/11 15:51
 * ******************************************
 * *加载动画扩展
 * ******************************************
 */

fun Context.showLoad(message:String="加载中...") {
    LoadTools.show(this,message)
}

fun hideLoad() {
    LoadTools.hide()
}
