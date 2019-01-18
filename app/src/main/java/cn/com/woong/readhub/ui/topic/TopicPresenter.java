package cn.com.woong.readhub.ui.topic;

import javax.inject.Inject;
import cn.com.woong.readhub.App;
import cn.com.woong.readhub.base.BasePresenter;
import cn.com.woong.readhub.constant.Constant;
import cn.com.woong.readhub.domain.apiservice.ReadhubApiService;
import cn.com.woong.readhub.domain.RxSchedulers;
import cn.com.woong.readhub.resp.TopicResp;
import io.reactivex.functions.Consumer;

/**
 * Created by wong on 2018/3/9.
 */

public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.Presenter {
//    @Inject
    public TopicPresenter() {
    }

    @Override
    public void getTopicNews(final String order) {
        App.Companion.apiService(ReadhubApiService.class)
                .apiTopic(order, Constant.TOPIC_PAGE_SIZE)
                .compose(RxSchedulers.<TopicResp>io_main())
                .compose(mView.<TopicResp>bindToLife())
                .subscribe(new Consumer<TopicResp>() {
                    @Override
                    public void accept(TopicResp topicResp) throws Exception {
                        if (topicResp != null && topicResp.data != null) {
                            mView.updateTopicData(order, topicResp.data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed();
                    }
                });
    }
}
