package cn.com.woong.readhub.utils

import cn.com.woong.readhub.bean.NewsMo
import cn.com.woong.readhub.bean.NewsDetailMo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object CommonUtils {
    @JvmStatic
    fun getTimeStampByReadhubDateString(date: String?): Long {
        date?.let {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA)
            format.timeZone = TimeZone.getTimeZone("UTC")

            try {
                return format.parse(date).time
            } catch (e: ParseException) {
                e.printStackTrace()
                return 0L
            }
        } ?: return 0L
    }

    @JvmStatic
    fun changeFromNewsDetailMo(newsDetailMo: NewsDetailMo): NewsMo {
        val newsMo = NewsMo()

        newsMo.title = newsDetailMo.title
        newsMo.summary = newsDetailMo.summary
        newsMo.summaryAuto = newsDetailMo.summaryAuto
        newsMo.url = newsDetailMo.url
        newsMo.mobileUrl = newsDetailMo.mobileUrl
        newsMo.siteName = newsDetailMo.siteName
        newsMo.language = newsDetailMo.language
        newsMo.authorName = newsDetailMo.authorName
        newsMo.publishDate = newsDetailMo.publishDate

        return newsMo
    }
}