package cn.com.woong.readhub.ui.topic.topicdetail;

import javax.inject.Inject;

import cn.com.woong.readhub.App;
import cn.com.woong.readhub.base.BasePresenter;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.domain.apiservice.ReadhubApiService;
import cn.com.woong.readhub.domain.RxSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by wong on 2018/4/22.
 */

public class TopicDetailPresenter extends BasePresenter<TopicDetailContract.View> implements TopicDetailContract.Presenter {

    @Inject
    TopicDetailPresenter () {}

    @Override
    public void getTopicDetail(String topicId) {
        mView.showLoading();
        App.apiService(ReadhubApiService.class)
                .apiTopicDetail(topicId)
                .compose(RxSchedulers.<TopicMo>io_main())
                .compose(mView.<TopicMo>bindToLife())
                .subscribe(new Consumer<TopicMo>() {
                    @Override
                    public void accept(TopicMo topicMo) throws Exception {
                        mView.hideLoading();
                        if (topicMo != null) {
                            mView.updateTopicDetail(topicMo);
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
