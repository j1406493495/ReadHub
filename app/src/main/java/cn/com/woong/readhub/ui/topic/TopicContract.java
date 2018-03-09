package cn.com.woong.readhub.ui.topic;

import cn.com.woong.readhub.base.BaseContract;

/**
 * Created by wong on 2018/3/9.
 */

public interface TopicContract {
    interface View extends BaseContract.BaseView {
        void updateData();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void refresh();

        void loadMore();
    }
}
