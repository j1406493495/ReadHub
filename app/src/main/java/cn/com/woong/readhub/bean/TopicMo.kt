package cn.com.woong.readhub.bean

import android.os.Parcelable
import cn.com.woong.readhub.db.NewsMoConverter
import kotlinx.android.parcel.Parcelize
import org.greenrobot.greendao.annotation.Convert
import org.greenrobot.greendao.annotation.Entity

@Entity
@Parcelize
data class TopicMo(var id: Long,
                   var createAt: String,
//                   @Convert(columnType = String::class.java, converter = NewsMoConverter::Class.java)
                   var newsArray: ArrayList<NewsMo>,
                   var order: Long,
                   var publishDate: String,
                   var summary: String,
                   var title: String,
                   var updateAt: String,
                   var timeline: String) : Parcelable