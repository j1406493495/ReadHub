package cn.com.woong.readhub.ui.topic.topicdetail;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.base.BaseDaggerActivity;
import cn.com.woong.readhub.bean.NewsMo;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.bean.TopicTimeLineMo;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter;
import cn.com.woong.readhub.ui.widget.newsview.NewsViewHolder;
import cn.com.woong.readhub.utils.CommonUtils;
import cn.com.woong.readhub.utils.ScreenShotUtil;
import dagger.android.AndroidInjection;
import io.reactivex.functions.Consumer;

/**
 * @author woong
 *         Created by wong on 2018/4/21.
 */

public class TopicDetailActivity extends BaseDaggerActivity<TopicDetailPresenter> implements TopicDetailContract.View {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.topic_detail_scrollview)
    ScrollView topicDetailScrollView;
    @BindView(R.id.tv_topic_title)
    TextView tvTopicTitle;
    @BindView(R.id.tv_topic_summary)
    TextView tvTopicSummary;
    @BindView(R.id.news_view_pager)
    ViewPager newsViewPager;
    @BindView(R.id.tv_news_indicator)
    TextView tvNewsIndicator;
    @BindView(R.id.tv_topic_timeline)
    TextView tvTopicTimeLine;
    @BindView(R.id.timeline_recycler_view)
    RecyclerView timelineRecyclerView;

    private static final String TOPIC_ID = "topic_id";
    private String mTopicId;

    private TimeLineAdapter mTimeLineAdapter;
    private NewsViewPagetAdapter mNewsViewPagetAdapter;
    private ArrayList<TopicTimeLineMo> mTopicTimeLineMos = new ArrayList<>();
    private ArrayList<NewsMo> mNewsMos = new ArrayList<>();
    private ArrayList<View> mNewsViewList = new ArrayList<>();

    public static void startTopicDetailActivity(Context context, String topicId) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra(TOPIC_ID, topicId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarVisibility(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.c1), 1);
        titleBar.setTitle(getString(R.string.readhub_str));
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.c1));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));

        titleBar.setLeftBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setRightImage(R.drawable.ic_save_pic, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RxPermissions(TopicDetailActivity.this)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    ScreenShotUtil.getBitmapByView(TopicDetailActivity.this, topicDetailScrollView);
                                }
                            }
                        });
            }
        });

        mTimeLineAdapter = new TimeLineAdapter();
        timelineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        timelineRecyclerView.setAdapter(mTimeLineAdapter);

        mNewsViewPagetAdapter = new NewsViewPagetAdapter();
        newsViewPager.setAdapter(mNewsViewPagetAdapter);
    }

    @Override
    protected void initData() {
        mTopicId = getIntent().getStringExtra(TOPIC_ID);

        if (mPresenter != null) {
            mPresenter.getTopicDetail(mTopicId);
        }

        newsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                tvNewsIndicator.setText(getString(R.string.see_more_news, position + 1, mNewsViewList.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void updateTopicDetail(TopicMo topicMo) {
        tvTopicTitle.setText(topicMo.title);
        tvTopicSummary.setText(topicMo.summary);

        mTopicTimeLineMos.clear();
        if (topicMo.timeline != null && topicMo.timeline.topics != null
                && topicMo.timeline.topics.size() > 0) {
            mTopicTimeLineMos.addAll(topicMo.timeline.topics);
        } else {
            tvTopicTimeLine.setVisibility(View.GONE);
            timelineRecyclerView.setVisibility(View.GONE);
        }
        mTimeLineAdapter.notifyDataSetChanged();

        mNewsMos.clear();
        mNewsViewList.clear();
        if (topicMo.getNewsArray() != null && topicMo.getNewsArray().size() > 0) {
            mNewsMos.addAll(topicMo.getNewsArray());
        }

        if (mNewsMos.size() > 0) {
            for(int i = 0, count = mNewsMos.size(); i < count; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.item_news, null);
                mNewsViewList.add(view);
            }

            if (mNewsMos.size() > 1) {
                tvNewsIndicator.setVisibility(View.VISIBLE);
                tvNewsIndicator.setText(getString(R.string.see_more_news, 1, mNewsMos.size()));
            } else {
                tvNewsIndicator.setVisibility(View.GONE);
            }
        } else {
            newsViewPager.setVisibility(View.GONE);
            tvNewsIndicator.setVisibility(View.GONE);
        }
        mNewsViewPagetAdapter.notifyDataSetChanged();
    }

    private class NewsViewPagetAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mNewsViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mNewsViewList.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            NewsViewHolder viewHolder = new NewsViewHolder(TopicDetailActivity.this, mNewsViewList.get(position));
            viewHolder.bind(mNewsMos.get(position));
            container.addView(mNewsViewList.get(position));
            return mNewsViewList.get(position);
        }
    }

    public class TimeLineAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_topic_timeline, parent, false);
            return new TimeLineViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TimeLineViewHolder) holder).onBind(position);
        }

        @Override
        public int getItemCount() {
            return mTopicTimeLineMos.size();
        }
    }

    private class TimeLineViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTimelineTime;
        private TextView tvTimelineLine;
        private TextView tvTimelineTitle;

        public TimeLineViewHolder(View itemView) {
            super(itemView);

            tvTimelineTime = itemView.findViewById(R.id.tv_timeline_time);
            tvTimelineLine = itemView.findViewById(R.id.tv_timeline_line);
            tvTimelineTitle = itemView.findViewById(R.id.tv_timeline_title);
        }

        public void onBind(int pos) {
            final TopicTimeLineMo topicTimeLineMo = mTopicTimeLineMos.get(pos);

            if (pos >= mTopicTimeLineMos.size() - 1) {
                tvTimelineLine.setVisibility(View.GONE);
            } else {
                tvTimelineLine.setVisibility(View.VISIBLE);
            }

            tvTimelineTime.setText(TimeUtils.millis2String(
                    CommonUtils.getTimeStampByReadhubDateString(topicTimeLineMo.createdAt),
                    new SimpleDateFormat("yyyy/MM/dd")));

            tvTimelineTitle.setText(topicTimeLineMo.title);

            tvTimelineTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TopicDetailActivity.startTopicDetailActivity(TopicDetailActivity.this, topicTimeLineMo.id);
                }
            });
        }
    }
}
