package cn.com.woong.readhub.ui.topic

import cn.com.woong.readhub.base.BaseContract
import cn.com.woong.readhub.bean.TopicMo


interface TopicContract {
    interface View : BaseContract.IView {
        fun updateTopicData(order: String, topicMos: ArrayList<TopicMo>)
    }

    interface Presenter : BaseContract.IPresenter<View> {
        fun getTopicNews(order: String)
    }
}