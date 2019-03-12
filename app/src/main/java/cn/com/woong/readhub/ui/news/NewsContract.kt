package cn.com.woong.readhub.ui.news

import cn.com.woong.readhub.base.BaseContract
import cn.com.woong.readhub.bean.NewsMo


interface NewsContract {
    interface View : BaseContract.View {
        fun updateTechNews(publishDate: String, newsMos: ArrayList<NewsMo>)
        fun updateDevelopNews(publishDate: String, newsMos: ArrayList<NewsMo>)
        fun updateBlockchainNews(publishDate: String, newsMos: ArrayList<NewsMo>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getTechNews(publishDate: String)
        fun getDevelopNews(publishDate: String)
        fun getBlockchainNews(publishDate: String)
    }
}