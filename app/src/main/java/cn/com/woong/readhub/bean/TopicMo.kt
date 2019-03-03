package cn.com.woong.readhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.greenrobot.greendao.annotation.Entity

@Parcelize
data class TopicMo(var id: Long,
                   var createAt: String,
                   var newsArray: ArrayList<NewsMo>,
                   var order: Long,
                   var publishDate: String,
                   var summary: String,
                   var title: String,
                   var updateAt: String,
                   var timeline: String) : Parcelable