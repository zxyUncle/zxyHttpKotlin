package com.zxy.http.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zsf on 2021/1/4 11:32
 * ******************************************
 * *
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ArticleData(
    var children: List<String> = listOf(),
    var courseId: Int? = 0, // 13
    var id: Int? = 0, // 409
    var name: String? = "", // 郭霖
    var order: Int? = 0, // 190001
    var parentChapterId: Int? = 0, // 407
    var userControlSetTop: Boolean? = false, // false
    var visible: Int? = 0 // 1
) : Parcelable