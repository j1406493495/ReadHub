package cn.com.woong.readhub.ui.news

import cn.com.woong.readhub.network.RxSchedulers
import cn.com.woong.readhub.network.ReadhubApiService
import cn.com.woong.readhub.App
import cn.com.woong.readhub.base.BaseContract
import cn.com.woong.readhub.base.BasePresenter
import cn.com.woong.readhub.constant.Constant
import com.blankj.utilcode.util.LogUtils
import io.reactivex.functions.Consumer

class NewsPresenter(var mView: BaseContract.IView) : BasePresenter<NewsContract.View>(), NewsContract.Presenter {
    override fun getView(): NewsContract.View {
        return mView as NewsContract.View
    }

    override fun getTechNews(publishDate: String) {
        LogUtils.i("apiTachNews === ")
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiTeachNews(publishDate, Constant.NEWS_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe( {
                    getView()?.updateTechNews(publishDate, it.data!!)
                }, {
                    LogUtils.e("apiTeachNews error == ${it}")
                })

    }

    override fun getDevelopNews(publishDate: String) {
        LogUtils.i("apiDevelopNews === ")
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiDevelopNews(publishDate, Constant.NEWS_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer {
                    getView()?.updateDevelopNews(publishDate, it.data!!)
                }, Consumer {
                    LogUtils.e("apiDevelopNews error == ${it}")
                })
    }

    override fun getBlockchainNews(publishDate: String) {
        LogUtils.i("apiBlockchainNews === ")
        App.sInstance.apiService(ReadhubApiService::class.java)
                ?.apiBlockchainNews(publishDate, Constant.NEWS_PAGE_SIZE)
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(Consumer {
                    getView()?.updateBlockchainNews(publishDate, it.data!!)
                }, Consumer {
                    LogUtils.e("apiBlockChainNews error == ${it}")
                })
    }
}
