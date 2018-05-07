package cn.com.woong.readhub.ui.mine.readlater;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;
import cn.com.woong.readhub.ui.widget.newsview.NewsView;

/**
 * @author wong
 * Created by wong on 2018/5/7.
 */

public class ReadLaterActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.readlater_tab_layout)
    TabLayout readlaterTabLayout;
    @BindView(R.id.readlater_view_pager)
    ViewPager readlaterViewPager;

    private RecyclerView mTopicRecycler;
    private NewsView mNewsView;
    private ArrayList<View> mReadLaterViews = new ArrayList<>();
    private ArrayList<TopicMo> mTopicMos = new ArrayList<>();
    private ArrayList<NewsMo> mNewsMos = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_readlater;
    }

    @Override
    protected void initView() {
        mTopicRecycler = new RecyclerView(this);
        mNewsView = new NewsView(this);

        mReadLaterViews.clear();
        mReadLaterViews.add(mTopicRecycler);
        mReadLaterViews.add(mNewsView);
    }

    @Override
    protected void initData() {

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
    }
}
