package cn.com.woong.readhub.ui.news;


import javax.inject.Inject;

import cn.com.woong.readhub.App;
import cn.com.woong.readhub.base.BasePresenter;
import cn.com.woong.readhub.constant.Constant;
import cn.com.woong.readhub.domain.apiservice.ReadhubApiService;
import cn.com.woong.readhub.domain.RxSchedulers;
import cn.com.woong.readhub.resp.NewsResp;
import io.reactivex.functions.Consumer;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {
    @Inject
    public NewsPresenter() {

    }

    @Override
    public void getTechNews(final String publishDate) {
        App.apiService(ReadhubApiService.class)
                .apiTeachNews(publishDate, Constant.NEWS_PAGE_SIZE)
                .compose(RxSchedulers.<NewsResp>io_main())
                .compose(mView.<NewsResp>bindToLife())
                .subscribe(new Consumer<NewsResp>() {
                    @Override
                    public void accept(NewsResp newsResp) throws Exception {
                        if (newsResp != null && newsResp.data != null) {
                            mView.updateTechNews(publishDate, newsResp.data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void getDevelopNews(final String publishDate) {
        App.apiService(ReadhubApiService.class)
                .apiDevelopNews(publishDate, Constant.NEWS_PAGE_SIZE)
                .compose(RxSchedulers.<NewsResp>io_main())
                .compose(mView.<NewsResp>bindToLife())
                .subscribe(new Consumer<NewsResp>() {
                    @Override
                    public void accept(NewsResp newsResp) throws Exception {
                        if (newsResp != null && newsResp.data != null) {
                            mView.updateDevelopNews(publishDate, newsResp.data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void getBlockchainNews(final String publishDate) {
        App.apiService(ReadhubApiService.class)
                .apiBlockchainNews(publishDate, Constant.NEWS_PAGE_SIZE)
                .compose(RxSchedulers.<NewsResp>io_main())
                .compose(mView.<NewsResp>bindToLife())
                .subscribe(new Consumer<NewsResp>() {
                    @Override
                    public void accept(NewsResp newsResp) throws Exception {
                        if (newsResp != null && newsResp.data != null) {
                            mView.updateBlockchainNews(publishDate, newsResp.data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
