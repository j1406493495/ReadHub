package cn.com.woong.readhub.ui.news

import cn.com.woong.readhub.network.resp.NewsResp
import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BasePresenter
import cn.com.woong.readhub.constant.Constant
import io.reactivex.functions.Consumer


class NewsPresenter : BasePresenter<NewsContract.View>(), NewsContract.Presenter {

    override fun getTechNews(publishDate: String) {
//        App.apiService(ReadhubApiService::class.java)
//                .apiTeachNews(publishDate, Constant.NEWS_PAGE_SIZE)
//                .compose(RxSchedulers.io_main<NewsResp>())
//                .compose(mView!!.bindToLife<NewsResp>())
//                .subscribe(object : Consumer<NewsResp>() {
//                    @Throws(Exception::class)
//                    fun accept(newsResp: NewsResp?) {
//                        if (newsResp != null && newsResp.data != null) {
//                            mView!!.updateTechNews(publishDate, newsResp.data!!)
//                        }
//                    }
//                }, object : Consumer<Throwable>() {
//                    @Throws(Exception::class)
//                    fun accept(throwable: Throwable) {
//                        mView!!.showFailed()
//                    }
//                })
    }

    override fun getDevelopNews(publishDate: String) {
//        App.apiService(ReadhubApiService::class.java)
//                .apiDevelopNews(publishDate, Constant.NEWS_PAGE_SIZE)
//                .compose(RxSchedulers.io_main<NewsResp>())
//                .compose(mView!!.bindToLife<NewsResp>())
//                .subscribe(object : Consumer<NewsResp>() {
//                    @Throws(Exception::class)
//                    fun accept(newsResp: NewsResp?) {
//                        if (newsResp != null && newsResp.data != null) {
//                            mView!!.updateDevelopNews(publishDate, newsResp.data!!)
//                        }
//                    }
//                }, object : Consumer<Throwable>() {
//                    @Throws(Exception::class)
//                    fun accept(throwable: Throwable) {
//                        mView!!.showFailed()
//                    }
//                })
    }

    override fun getBlockchainNews(publishDate: String) {
//        App.apiService(ReadhubApiService::class.java)
//                .apiBlockchainNews(publishDate, Constant.NEWS_PAGE_SIZE)
//                .compose(RxSchedulers.io_main<NewsResp>())
//                .compose(mView!!.bindToLife<NewsResp>())
//                .subscribe(object : Consumer<NewsResp>() {
//                    @Throws(Exception::class)
//                    fun accept(newsResp: NewsResp?) {
//                        if (newsResp != null && newsResp.data != null) {
//                            mView!!.updateBlockchainNews(publishDate, newsResp.data!!)
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
