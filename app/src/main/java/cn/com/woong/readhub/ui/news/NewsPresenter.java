package cn.com.woong.readhub.ui.news;

import javax.inject.Inject;

import cn.com.woong.readhub.base.BasePresenter;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {
    @Inject
    public NewsPresenter() {

    }

    @Override
    public void getTechNews(String publishDate) {

    }

    @Override
    public void getDevelopNews(String publishDate) {

    }

    @Override
    public void getBlockchainNews(String publishDate) {

    }
}
