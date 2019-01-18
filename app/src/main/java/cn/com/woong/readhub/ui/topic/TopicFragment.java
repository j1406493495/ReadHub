package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseDaggerFragment;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.ui.widget.EmptyView;
import dagger.android.support.AndroidSupportInjection;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author woong
 *         Created by wong on 2018/3/9.
 */
public class TopicFragment extends BaseDaggerFragment<TopicPresenter> implements TopicContract.View {
    @BindView(R.id.topic_refresh_layout)
    EasyRefreshLayout topicRefreshLayout;
    @BindView(R.id.topic_recycler_view)
    RecyclerView topicRecyclerView;

//    @Inject
    TopicAdapter mTopicAdapter = new TopicAdapter(getActivity());

    private String mLastOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(View view) {
        topicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topicRecyclerView.setAdapter(mTopicAdapter);
    }

    @Override
    protected void initData() {
        mLastOrder = "";
        LogUtils.i("TopicFragment initData mPresenter === " + mPresenter);
        if (mPresenter != null) {
            mPresenter.getTopicNews(mLastOrder);
        }

        topicRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                if (mPresenter != null) {
                    mPresenter.getTopicNews(mLastOrder);
                }
            }

            @Override
            public void onRefreshing() {
                if (mPresenter != null) {
                    mLastOrder = "";
                    mPresenter.getTopicNews(mLastOrder);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        topicRefreshLayout.loadMoreComplete();
    }

    public void refreshTopicData() {
        mLastOrder = "";
        topicRecyclerView.scrollToPosition(0);
        if (mPresenter != null) {
            mPresenter.getTopicNews(mLastOrder);
        }
    }

    @Override
    public void updateTopicData(String order, ArrayList<TopicMo> topicMos) {
        topicRefreshLayout.refreshComplete();
        topicRefreshLayout.loadMoreComplete();

        mTopicAdapter.updateTopics(TextUtils.isEmpty(order), topicMos);
        mLastOrder = String.valueOf(topicMos.get(topicMos.size() - 1).order);
    }
}
