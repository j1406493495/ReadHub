package cn.com.woong.readhub.ui.topic;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;

import javax.inject.Inject;

import cn.com.woong.readhub.base.BaseContract;
import cn.com.woong.readhub.base.BasePresenter;
import cn.com.woong.readhub.domain.ApiService;
import cn.com.woong.readhub.domain.RxSchedulers;
import cn.com.woong.readhub.domain.exception.ResultException;
import cn.com.woong.readhub.resp.BaseResponse;

/**
 * Created by wong on 2018/3/9.
 */

public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.Presenter {
    @Inject
    public TopicPresenter() {
    }

    @Override
    public void refresh() {
            mView.showLoading();
            BaseApiApp.apiService(ApiService.class)
                    .api
                    .compose(RxSchedulers.<BaseResponse>io_main())
                    .subscribe(new BaseCallBack<BaseResponse>(mView) {
                        @Override
                        protected void onSuccess(@NonNull BaseResponse response) {
                        }

                        @Override
                        protected void onFinish(Object... objects) {
                            mView.hideLoading();
                            if (objects.length > 0) {
                                if (objects[0] instanceof ResultException) {
                                    ResultException resultException = (ResultException) objects[0];
                                    ToastUtils.showLong(resultException.msg);
                                }
                            }
                        }
                    });
        }

    }

    @Override
    public void loadMore() {

    }
}
