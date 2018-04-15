package cn.com.woong.readhub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.BarUtils;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.ui.mine.MineFragment;
import cn.com.woong.readhub.ui.news.NewsFragment;
import cn.com.woong.readhub.ui.topic.TopicFragment;
import cn.com.woong.readhub.ui.widget.ScrollViewPager;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;

/**
 * @author woong
 *         Created by wong on 2018/3/7.
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.view_pager)
    ScrollViewPager viewPager;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    @BindView(R.id.rb_tab_topic)
    RadioButton rbTabTopic;
    @BindView(R.id.rb_tab_news)
    RadioButton rbTabNews;
    @BindView(R.id.rb_tab_mine)
    RadioButton rbTabMine;

    ViewPagerAdapter mViewPagerAdapter;

    private ArrayList<String> mTitleStrs = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private TopicFragment mTopicFragment;
    private NewsFragment mNewsFragment;
    private MineFragment mMineFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();

        BarUtils.setStatusBarVisibility(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.c1), 1);
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.c1));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));
    }

    @Override
    protected void initData() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(0);
        rbTabTopic.setChecked(true);
        titleBar.setTitle(mTitleStrs.get(0));

        initListener();
    }

    private void initListener() {
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int pos = 0;
                switch (checkedId) {
                    case R.id.rb_tab_topic:
                        pos = 0;
                        break;
                    case R.id.rb_tab_news:
                        pos = 1;
                        break;
                    case R.id.rb_tab_mine:
                        pos = 2;
                        break;
                    default:
                        break;
                }
                viewPager.setCurrentItem(pos);
                titleBar.setTitle(mTitleStrs.get(pos));
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                titleBar.setTitle(mTitleStrs.get(position));
                switch (position) {
                    case 0:
                        rbTabTopic.setChecked(true);
                        break;
                    case 1:
                        rbTabNews.setChecked(true);
                        break;
                    case 2:
                        rbTabMine.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        mTopicFragment = new TopicFragment();
        mNewsFragment = new NewsFragment();
        mMineFragment = new MineFragment();
        mFragments.add(mTopicFragment);
        mFragments.add(mNewsFragment);
        mFragments.add(mMineFragment);
        mTitleStrs.add(getString(R.string.tab_hot));
        mTitleStrs.add(getString(R.string.tab_info));
        mTitleStrs.add(getString(R.string.tab_mine));
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
