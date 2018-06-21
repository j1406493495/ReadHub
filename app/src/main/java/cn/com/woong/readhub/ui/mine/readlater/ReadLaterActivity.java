package cn.com.woong.readhub.ui.mine.readlater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.blankj.utilcode.util.BarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.base.BaseDaggerActivity;
import cn.com.woong.readhub.base.BasePresenter;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.db.DBManager;
import cn.com.woong.readhub.eventbus.Event;
import cn.com.woong.readhub.ui.topic.TopicAdapter;
import cn.com.woong.readhub.ui.widget.EmptyView;
import cn.com.woong.readhub.ui.widget.ShowEmptyRecyclerView;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author wong
 *         Created by wong on 2018/5/7.
 */

public class ReadLaterActivity extends BaseDaggerActivity<BasePresenter> {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.readlater_tab_layout)
    TabLayout readlaterTabLayout;
    @BindView(R.id.readlater_view_pager)
    ViewPager readlaterViewPager;

    @Inject
    ShowEmptyRecyclerView mNewsRecycler;
    @Inject
    ShowEmptyRecyclerView mTopicRecycler;
    @Inject
    TopicAdapter mTopicAdapter;
    @Inject
    NewsAdapter mNewsAdapter;

    private ViewPagerAdapter mViewPagerAdapter;
    private ArrayList<View> mReadLaterViews = new ArrayList<>();
    private ArrayList<TopicMo> mTopicMos = new ArrayList<>();
    private ArrayList<NewsMo> mNewsMos = new ArrayList<>();
    private String[] mTabTitle = {"热门", "资讯"};

    public static void startReadLaterActivity(Activity activity) {
        Intent intent = new Intent(activity, ReadLaterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_readlater;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.c1), 1);
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.c1));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));

        titleBar.setTitle(getString(R.string.read_later));
        titleBar.setLeftBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mReadLaterViews.clear();
        mReadLaterViews.add(mTopicRecycler);
        mReadLaterViews.add(mNewsRecycler);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);

        mViewPagerAdapter = new ViewPagerAdapter();
        readlaterViewPager.setAdapter(mViewPagerAdapter);
        readlaterTabLayout.setupWithViewPager(readlaterViewPager);

        mTopicMos = (ArrayList<TopicMo>) DBManager.getInstance(this).queryAllTopicMo();
        mNewsMos = (ArrayList<NewsMo>) DBManager.getInstance(this).queryAllNewsMo();

        mTopicAdapter.showDelete(true);
        mTopicRecycler.getRecyclerView().setAdapter(mTopicAdapter);
        mTopicRecycler.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mTopicAdapter.updateTopics(true, mTopicMos);

        mNewsAdapter.showDelete(true);
        mNewsRecycler.getRecyclerView().setAdapter(mNewsAdapter);
        mNewsRecycler.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mNewsAdapter.updateNews(true, mNewsMos);

        if (mTopicMos.size() == 0) {
            mTopicRecycler.showEmptyView(true, getString(R.string.no_readlater));
        }

        if (mNewsMos.size() == 0) {
            mNewsRecycler.showEmptyView(true, getString(R.string.no_readlater));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void removeTopicEvent(Event.ReadLaterTopicRemoveEvent event) {
        mTopicAdapter.removeTopic(event.position);

        mTopicMos.remove(event.position);
        if (mTopicMos.size() == 0) {
            mTopicRecycler.showEmptyView(true, getString(R.string.no_readlater));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void removeNewsEvent(Event.ReadLaterNewsRemoveEvent event) {
        mNewsAdapter.removeNews(event.position);

        mNewsMos.remove(event.position);
        if (mNewsMos.size() == 0) {
            mNewsRecycler.showEmptyView(true, getString(R.string.no_readlater));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mReadLaterViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mReadLaterViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mReadLaterViews.get(position));
            return mReadLaterViews.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }
    }
}
