package cn.com.woong.readhub.ui.topic.topicdetail;

import cn.com.woong.readhub.base.BaseContract;
import cn.com.woong.readhub.bean.TopicMo;

/**
 * Created by wong on 2018/4/22.
 */

public interface TopicDetailContract {
    interface View extends BaseContract.BaseView {
        void updateTopicDetail(TopicMo topicMo);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getTopicDetail(String topicId);
    }
}
