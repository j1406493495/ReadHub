package cn.com.woong.readhub.utils

import cn.com.woong.readhub.bean.NewsMo
import cn.com.woong.readhub.bean.NewsDetailMo
import jdk.nashorn.internal.objects.NativeDate.getTime


class CommonUtils {
    @JvmStatic
    fun getTimeStampByReadhubDateString(date: String?): Long? {
        if (date == null) {
            return 0L
        }

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA)
        format.setTimeZone(TimeZone.getTimeZone("UTC"))

        try {
            return format.parse(date).getTime()
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0L
        }

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