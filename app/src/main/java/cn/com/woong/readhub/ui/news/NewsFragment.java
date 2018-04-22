package cn.com.woong.readhub.ui.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.ui.widget.newsview.NewsView;
import cn.com.woong.readhub.utils.CommonUtils;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by wong on 2018/3/9.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {
    @BindView(R.id.news_tab_layout)
    TabLayout newsTabLayout;
    @BindView(R.id.news_view_pager)
    ViewPager newsViewPager;

    private NewsViewPagerAdapter mNewsViewPagerAdapter;
    private ArrayList<NewsView> mNewsViewList = new ArrayList<>();
    private ArrayList<String> mPublishDateList = new ArrayList<>();
    private String[] mTabTitle = {"科技动态", "开发者资讯", "区块链快讯"};
    private int mCurrentTabPos = 0;

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

    @Override
    protected void initData() {
        if (mPresenter != null) {
            mPresenter.getTechNews("");
        }

        newsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentTabPos = tab.getPosition();
                if (mPresenter != null) {
                    switch (tab.getPosition()) {
                        case 0:
                            mPresenter.getTechNews("");
                            break;
                        case 1:
                            mPresenter.getDevelopNews("");
                            break;
                        case 2:
                            mPresenter.getBlockchainNews("");
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void initViewPager() {
        for (int i = 0, count = 3; i < count; i++) {
            final int pos = i;
            NewsView newsView = new NewsView(getActivity());
            newsView.setOnNewsListener(new NewsView.OnNewsListener() {
                @Override
                public void onNewsLoadMore() {
                    if (mPresenter != null) {
                        switch (pos) {
                            case 0:
                                mPresenter.getTechNews(mPublishDateList.get(0));
                                break;
                            case 1:
                                mPresenter.getDevelopNews(mPublishDateList.get(1));
                                break;
                            case 2:
                                mPresenter.getBlockchainNews(mPublishDateList.get(2));
                                break;
                            default:
                                break;
                        }
                    }
                }

                @Override
                public void onNewsRefreshing() {
                    if (mPresenter != null) {
                        mPublishDateList.set(pos, "");
                        switch (pos) {
                            case 0:
                                mPresenter.getTechNews("");
                                break;
                            case 1:
                                mPresenter.getDevelopNews("");
                                break;
                            case 2:
                                mPresenter.getBlockchainNews("");
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
            mNewsViewList.add(newsView);
            mPublishDateList.add("");
        }

        mNewsViewPagerAdapter = new NewsViewPagerAdapter();
        newsViewPager.setAdapter(mNewsViewPagerAdapter);
        newsTabLayout.setupWithViewPager(newsViewPager);
    }

    public void refreshNewsData() {
        if (mPresenter != null) {
            mPublishDateList.set(mCurrentTabPos, "");
            switch (mCurrentTabPos) {
                case 0:
                    mPresenter.getTechNews("");
                    break;
                case 1:
                    mPresenter.getDevelopNews("");
                    break;
                case 2:
                    mPresenter.getBlockchainNews("");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void updateTechNews(String publishDate, ArrayList<NewsMo> newsMos) {
        mPublishDateList.set(0, String.valueOf(CommonUtils.getTimeStampByReadhubDateString(
                newsMos.get(newsMos.size() - 1).publishDate)));
        mNewsViewList.get(0).updateNews(TextUtils.isEmpty(publishDate), newsMos);
    }

    @Override
    public void updateDevelopNews(String publishDate, ArrayList<NewsMo> newsMos) {
        mPublishDateList.set(1, String.valueOf(CommonUtils.getTimeStampByReadhubDateString(
                newsMos.get(newsMos.size() - 1).publishDate)));
        mNewsViewList.get(1).updateNews(TextUtils.isEmpty(publishDate), newsMos);
    }

    @Override
    public void updateBlockchainNews(String publishDate, ArrayList<NewsMo> newsMos) {
        mPublishDateList.set(2, String.valueOf(CommonUtils.getTimeStampByReadhubDateString(
                newsMos.get(newsMos.size() - 1).publishDate)));
        mNewsViewList.get(2).updateNews(TextUtils.isEmpty(publishDate), newsMos);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewsViewList.clear();
        mPublishDateList.clear();
    }

    private class NewsViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mNewsViewList.size();
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

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }
    }
}
