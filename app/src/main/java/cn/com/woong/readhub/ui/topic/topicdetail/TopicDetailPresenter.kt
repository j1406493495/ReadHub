package cn.com.woong.readhub.ui.topic.topicdetail

import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BaseContract
import cn.com.woong.readhub.base.BasePresenter
import com.blankj.utilcode.util.LogUtils
import io.reactivex.functions.Consumer


class TopicDetailPresenter(var mView: BaseContract.IView) : BasePresenter<TopicDetailContract.View>(), TopicDetailContract.Presenter {
    override fun getView(): TopicDetailContract.View {
        return mView as TopicDetailContract.View
    }

    override fun getTopicDetail(topicId: String) {
        getView()!!.showLoading()
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiTopicDetail(topicId)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer {
                    getView()?.hideLoading()
                    getView()?.updateTopicDetail(it)
                }, Consumer {
                    LogUtils.e("apiTopicDetail == ${it}")
                })
    }
}