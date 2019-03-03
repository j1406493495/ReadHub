package cn.com.woong.readhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopicTimeLineMo(var id: String,
                           var title: String,
                           var createdAt: String) : Parcelable
