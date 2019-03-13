package cn.com.woong.readhub.ui.news

import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BasePresenter
import cn.com.woong.readhub.constant.Constant
import com.blankj.utilcode.util.LogUtils
import io.reactivex.functions.Consumer


class NewsPresenter : BasePresenter<NewsContract.View>(), NewsContract.Presenter {

    override fun getTechNews(publishDate: String) {
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiTeachNews(publishDate, Constant.NEWS_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe( {
                    mView?.updateTechNews(publishDate, it.data!!)
                }, {
                    LogUtils.e("apiTeachNews error == ${it}")
                })

    }

    override fun getDevelopNews(publishDate: String) {
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiDevelopNews(publishDate, Constant.NEWS_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer {
                    mView?.updateDevelopNews(publishDate, it.data!!)
                }, Consumer {
                    LogUtils.e("apiDevelopNews error == ${it}")
                })
    }

    override fun getBlockchainNews(publishDate: String) {
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiBlockchainNews(publishDate, Constant.NEWS_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer {
                    mView?.updateBlockchainNews(publishDate, it.data!!)
                }, Consumer {
                    LogUtils.e("apiBlockChainNews error == ${it}")
                })
    }
}
