package com.zxy.httpnet.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by zxy on 2019/8/26-14:44
 * Class functions
 * ******************************************
 *  网路工具类
 * ******************************************
 */
class NetWorkUtils {
    companion object {
        /**JsonUtil
         * 判断是否在Wifi下
         * @param mContext Context
         * @return Boolean
         */
        fun isWifi(mContext: Context): Boolean {
            val systemService = mContext.getSystemService(Context.CONNECTIVITY_SERVICE)
            var connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null
                && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI
            ) {
                return true
            }
            return false
        }

        /**
         * 是否有网路
         * @param context Context
         * @return Boolean
         */
        fun isNetAvailable(context: Context): Boolean {
            var isAvailable = false;
            var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo = cm.getActiveNetworkInfo()
            if (networkInfo != null && networkInfo.isAvailable()) {
                isAvailable = true
            }
            return isAvailable;
        }
    }
}