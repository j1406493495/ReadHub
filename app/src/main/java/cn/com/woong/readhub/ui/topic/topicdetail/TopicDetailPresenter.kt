package cn.com.woong.readhub.ui.topic.topicdetail

import cn.com.woong.readhub.bean.TopicDetailMo
import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BasePresenter
import com.blankj.utilcode.util.LogUtils
import io.reactivex.functions.Consumer


class TopicDetailPresenter : BasePresenter<TopicDetailContract.View>(), TopicDetailContract.Presenter {

    override fun getTopicDetail(topicId: String) {
        mView!!.showLoading()
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiTopicDetail(topicId)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer {
                    mView?.hideLoading()
                    mView?.updateTopicDetail(it)
                }, Consumer {
                    LogUtils.e("apiTopicDetail == ${it}")
                })
    }
}