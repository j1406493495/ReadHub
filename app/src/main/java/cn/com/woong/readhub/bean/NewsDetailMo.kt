package cn.com.woong.readhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsDetailMo(var id: String,
                        var title: String,
                        var summary: String,
                        var summaryAuto: String,
                        var url: String,
                        var mobileUrl: String,
                        var siteName: String,
                        var language: String,
                        var authorName: String,
                        var publishDate: String) : Parcelable
