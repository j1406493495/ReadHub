package cn.com.woong.readhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopicDetailMo(var id: String,
                         var createAt: String,
                         var newsArray: ArrayList<NewsDetailMo>,
                         var order: Long,
                         var publishDate: String,
                         var summary: String,
                         var title: String,
                         var updateAt: String,
                         var timeline: TimeLineMo) : Parcelable
