package cn.com.woong.readhub.ui.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.ui.widget.NewsView;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {
    @BindView(R.id.news_tab_layout)
    TabLayout newsTabLayout;
    @BindView(R.id.news_view_pager)
    ViewPager newsViewPager;

    NewsViewPagerAdapter mNewsViewPagerAdapter;
    ArrayList<NewsView> mNewsViewList = new ArrayList<>();
    String[] mTabTitle = {"科技动态", "开发者资讯", "区块链快讯"};
    private int mCurrentTabPos;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View view) {
        initViewPager();
    }

    private void initViewPager() {
        mCurrentTabPos = 0;
        for (int i = 0, count = 3; i < count; i++) {
            NewsView newsView = new NewsView(getActivity());
            newsView.setOnNewsListener(new NewsView.OnNewsListener() {
                @Override
                public void onNewsLoadMore() {

                }

                @Override
                public void onNewsRefreshing() {

                }
            });
            mNewsViewList.add(newsView);
        }

        newsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentTabPos = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mNewsViewPagerAdapter = new NewsViewPagerAdapter();
        newsViewPager.setAdapter(mNewsViewPagerAdapter);
        newsTabLayout.setupWithViewPager(newsViewPager);
    }

    @Override
    public void setHomeBanners() {

    }

    private class NewsViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mNewsViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mNewsViewList.get(position));
            return mNewsViewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mNewsViewList.get(position));
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }
    }
}
