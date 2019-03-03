package cn.com.woong.readhub.db

import cn.com.woong.readhub.bean.NewsMo
import org.greenrobot.greendao.converter.PropertyConverter
import java.util.*


class NewsMoConverter : PropertyConverter<ArrayList<NewsMo>, String> {
    override fun convertToDatabaseValue(entityProperty: ArrayList<NewsMo>?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun convertToEntityProperty(databaseValue: String?): ArrayList<NewsMo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
//    override fun convertToEntityProperty(databaseValue: String?): ArrayList<NewsMo>? {
//        if (databaseValue == null) {
//            return null
//        }
//
//        val newsMos = ArrayList()
//        newsMos.clear()
//
//        val newsMoList = Arrays.asList(databaseValue.split("##".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
//        for (newsMoString in newsMoList) {
//            val newsMoInfoList = Arrays.asList(newsMoString.split("&&".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray())
//
//            if (newsMoInfoList.size >= 9) {
//                val newsMo = NewsMo()
//                newsMo.id = java.lang.Long.parseLong(newsMoInfoList.get(0))
//                newsMo.title = newsMoInfoList.get(1)
//                newsMo.summary = newsMoInfoList.get(2)
//                newsMo.summaryAuto = newsMoInfoList.get(3)
//                newsMo.siteName = newsMoInfoList.get(4)
//                newsMo.authorName = newsMoInfoList.get(5)
//                newsMo.mobileUrl = newsMoInfoList.get(6)
//                newsMo.publishDate = newsMoInfoList.get(7)
//                newsMo.language = newsMoInfoList.get(8)
//
//                newsMos.add(newsMo)
//            }
//        }
//
//        return newsMos
//    }
//
//    override fun convertToDatabaseValue(entityProperty: ArrayList<NewsMo>?): String? {
//        if (entityProperty == null) {
//            return null
//        }
//
//        val stringBuilder = StringBuilder()
//        for ((id, title, summary, summaryAuto, _, mobileUrl, siteName, language, authorName, publishDate) in entityProperty) {
//            stringBuilder.append(id)
//            stringBuilder.append("&&")
//            stringBuilder.append(title)
//            stringBuilder.append("&&")
//            stringBuilder.append(summary)
//            stringBuilder.append("&&")
//            stringBuilder.append(summaryAuto)
//            stringBuilder.append("&&")
//            stringBuilder.append(siteName)
//            stringBuilder.append("&&")
//            stringBuilder.append(authorName)
//            stringBuilder.append("&&")
//            stringBuilder.append(mobileUrl)
//            stringBuilder.append("&&")
//            stringBuilder.append(publishDate)
//            stringBuilder.append("&&")
//            stringBuilder.append(language)
//            stringBuilder.append("##")
//        }
//
//        return stringBuilder.toString()
//    }
}