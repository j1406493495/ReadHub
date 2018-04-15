package cn.com.woong.readhub.ui.topic;

import java.util.ArrayList;

import cn.com.woong.readhub.base.BaseContract;
import cn.com.woong.readhub.bean.TopicMo;

/**
 * Created by wong on 2018/3/9.
 */

public interface TopicContract {
    interface View extends BaseContract.BaseView {
        void updateTopicData(String order, ArrayList<TopicMo> topicMos);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTopicNews(String order);
    }
}
