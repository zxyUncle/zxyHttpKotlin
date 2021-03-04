package com.zxy.http.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zxy on 2020/8/3 16:07
 * ******************************************
 * * 1.3、用户登录
 * ******************************************
 */
@Parcelize
data class LoginInfoBean(
    var channel: String? = "", // yunna
    var code: String? = "0", // 0000
    var success: Boolean? = false, // true
    var token: String? = "", // d7578409db99468281315aa318414312
    var userId: String? = "", // 200716172249312UA000249
    var userInfos: MutableList<ChannelInfoBean>? = null
) : Parcelable {
    //渠道
    @Parcelize
    data class ChannelInfoBean(
        var channelName: String? = null,  ////  渠道名称渠道名称
        var mobile: String? = null,
        var realName: String? = null,
        var registerChannel: String? = null,
        var userId: String? = null
    ) : Parcelable {}
}