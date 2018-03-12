package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ajguan.library.EasyRefreshLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.ui.common.NewsAdapter;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author woong
 * Created by wong on 2018/3/9.
 */
public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.View {
    @BindView(R.id.topic_recycler_view)
    RecyclerView topicRecyclerView;
    @BindView(R.id.topic_refresh_layout)
    EasyRefreshLayout topicRefreshLayout;

    NewsAdapter mNewsAdapter;

    private ArrayList<NewsMo> mNewsMos = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(View view) {
        mNewsAdapter = new NewsAdapter(getActivity());
        topicRecyclerView.setAdapter(mNewsAdapter);
        topicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        topicRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                topicRefreshLayout.loadMoreComplete();
            }

            @Override
            public void onRefreshing() {
                topicRefreshLayout.refreshComplete();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0, count = 7; i < count; i++) {
            NewsMo newsMo = new NewsMo();
            newsMo.title = "new === 供钢丝等冲等宏观第刚抵港的创恒第刚吃第刚抵过创恒创高创恒创";
            newsMo.publishDate = "11:22";
            newsMo.summary = "测试供广大的当红的当黄黄扫就发丧公 a 刚僧洪邓程红城等会冲等红第刚抵僧三创刚抵过创刚创刚创刚抵创刚创长谷川长凳坑红僧洪";
            mNewsMos.add(newsMo);
        }

        mNewsAdapter.updateNews(mNewsMos, 1);
    }

    @Override
    public void updateData() {

    }
}
