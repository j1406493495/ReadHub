package cn.com.woong.readhub.ui.topic.topicdetail

import cn.com.woong.readhub.base.BaseContract
import cn.com.woong.readhub.bean.TopicDetailMo

interface TopicDetailContract {
    interface View : BaseContract.View {
        fun updateTopicDetail(topicDetailMo: TopicDetailMo)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getTopicDetail(topicId: String)
    }
}