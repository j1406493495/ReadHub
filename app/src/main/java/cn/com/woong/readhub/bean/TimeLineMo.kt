package cn.com.woong.readhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimeLineMo(var topics: ArrayList<TopicTimeLineMo>? = null,
                      var message: String,
                      var errorCode: Int,
                      var keywords: ArrayList<String>? = null) :  Parcelable


