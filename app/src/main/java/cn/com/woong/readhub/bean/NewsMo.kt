package cn.com.woong.readhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id

@Entity
@Parcelize
data class NewsMo(
        @Id
        var id: Long,
        var title: String,
        var summary: String,
        var summaryAuto: String,
        var url: String,
        var mobileUrl: String,
        var siteName: String,
        var language: String,
        var authorName: String,
        var publishDate: String) : Parcelable
