package cn.com.woong.readhub.ui.topic

import android.R.attr.data
import android.annotation.SuppressLint
import cn.com.woong.readhub.network.resp.TopicResp
import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BasePresenter
import cn.com.woong.readhub.constant.Constant
import com.blankj.utilcode.util.LogUtils
import io.reactivex.functions.Consumer


class TopicPresenter : BasePresenter<TopicContract.View>(), TopicContract.Presenter {

    @SuppressLint("CheckResult")
    override fun getTopicNews(order: String) {
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiTopic(order, Constant.TOPIC_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer<TopicResp> { topicResp ->
                    if (topicResp.data != null) {
                        mView?.updateTopicData(order, topicResp.data!!)
                    }
                }, Consumer<Throwable> {
                    LogUtils.e("apiTopic error ==== ${it}")
                })
    }
}