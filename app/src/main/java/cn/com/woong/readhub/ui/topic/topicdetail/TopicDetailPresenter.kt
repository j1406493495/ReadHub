package cn.com.woong.readhub.ui.topic.topicdetail

import cn.com.woong.readhub.bean.TopicDetailMo
import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BasePresenter


class TopicDetailPresenter : BasePresenter<TopicDetailContract.View>(), TopicDetailContract.Presenter {

    override fun getTopicDetail(topicId: String) {
        mView!!.showLoading()
//        App.apiService(ReadhubApiService::class.java)
//                .apiTopicDetail(topicId)
//                .compose(RxSchedulers.io_main<TopicDetailMo>())
//                .compose(mView!!.bindToLife<TopicDetailMo>())
//                .subscribe(object : Consumer<TopicDetailMo>() {
//                    @Throws(Exception::class)
//                    fun accept(topicDetailMo: TopicDetailMo?) {
//                        mView!!.hideLoading()
//                        if (topicDetailMo != null) {
//                            mView!!.updateTopicDetail(topicDetailMo)
//                        }
//                    }
//                }, object : Consumer<Throwable>() {
//                    @Throws(Exception::class)
//                    fun accept(throwable: Throwable) {
//                        mView!!.showFailed()
//                    }
//                })
    }
}