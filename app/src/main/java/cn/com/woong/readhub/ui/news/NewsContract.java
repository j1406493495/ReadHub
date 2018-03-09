package cn.com.woong.readhub.ui.news;

import cn.com.woong.readhub.base.BaseContract;

/**
 * Created by wong on 2018/3/9.
 */

public interface NewsContract {
    interface View extends BaseContract.BaseView {
        void setHomeBanners();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refresh();
    }
}
