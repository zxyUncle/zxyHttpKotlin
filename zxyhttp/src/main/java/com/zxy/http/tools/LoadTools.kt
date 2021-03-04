package com.zxy.http.tools

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD
import com.zxy.http.R

/**
 * Created by zxy on 2020/8/6 16:06
 * ******************************************
 * *
 * ******************************************
 */
class LoadTools {
    var kProgressHUD: KProgressHUD? = null
    var isAlowShow: Boolean = true

    //zxy 单例模式
    private constructor() {}

    companion object {
        @Volatile
        private var instance: LoadTools? = null

        fun instance(): LoadTools {
            if (instance == null) {
                synchronized(LoadTools::class.java) {
                    if (instance == null) {
                        instance = LoadTools()
                    }
                }
            }
            return instance!!
        }
    }

    fun show(mContext: Context) {
        if (kProgressHUD == null && isAlowShow) {
            kProgressHUD = KProgressHUD.create(mContext)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(mContext.resources.getString(R.string.yn_loading))
//                .setDetailsLabel("Downloading data")
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.1f)
                    .show()
        } else {
            if (!kProgressHUD!!.isShowing && isAlowShow) {
                hide()
            }
        }

    }

    fun hide() {
        if (kProgressHUD != null && isAlowShow) {
            kProgressHUD?.dismiss()
            kProgressHUD = null
        }
    }


    fun showMultistage(mContext: Context) {
        isAlowShow = false
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(mContext)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(mContext.resources.getString(R.string.yn_loading))
//                .setDetailsLabel("Downloading data")
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.1f)
                    .show()
        } else {
            if (!kProgressHUD!!.isShowing) {
                hideMultistage()
            }
        }
    }

    fun hideMultistage() {
        isAlowShow = true
        if (kProgressHUD != null) {
            kProgressHUD?.dismiss()
            kProgressHUD = null
        }
    }

}