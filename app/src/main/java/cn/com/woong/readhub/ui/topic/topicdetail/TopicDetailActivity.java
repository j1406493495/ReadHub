package cn.com.woong.readhub.ui.topic.topicdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.bean.TopicTimeLineMo;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter;
import cn.com.woong.readhub.utils.CommonUtils;
import dagger.android.AndroidInjection;

/**
 * @author woong
 *         Created by wong on 2018/4/21.
 */

public class TopicDetailActivity extends BaseActivity<TopicDetailPresenter> implements TopicDetailContract.View {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.tv_topic_title)
    TextView tvTopicTitle;
    @BindView(R.id.tv_topic_summary)
    TextView tvTopicSummary;
    @BindView(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;
    @BindView(R.id.tv_topic_timeline)
    TextView tvTopicTimeLine;
    @BindView(R.id.timeline_recycler_view)
    RecyclerView timelineRecyclerView;

    @Inject
    NewsAdapter mNewsAdapter;

    private static final String TOPIC_ID = "topic_id";
    private String mTopicId;

    TimeLineAdapter mTimeLineAdapter;
    private ArrayList<TopicTimeLineMo> mTopicTimeLineMos = new ArrayList<>();

    public static void startTopicDetailActivity(Context context, String topicId) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra(TOPIC_ID, topicId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
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

        titleBar.setRightImage(R.drawable.ic_share_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTimeLineAdapter = new TimeLineAdapter();
        timelineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        timelineRecyclerView.setAdapter(mTimeLineAdapter);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(mNewsAdapter);
    }

    @Override
    protected void initData() {
        mTopicId = getIntent().getStringExtra(TOPIC_ID);

        if (mPresenter != null) {
            mPresenter.getTopicDetail(mTopicId);
        }
    }

    @Override
    public void updateTopicDetail(TopicMo topicMo) {
        tvTopicTitle.setText(topicMo.title);
        tvTopicSummary.setText(topicMo.summary);

        mNewsAdapter.updateNews(true, topicMo.newsArray);

        mTopicTimeLineMos.clear();
        if (topicMo.timeline != null && topicMo.timeline.topics != null
                && topicMo.timeline.topics.size() > 0) {
            mTopicTimeLineMos.addAll(topicMo.timeline.topics);
        } else {
            tvTopicTimeLine.setVisibility(View.GONE);
            timelineRecyclerView.setVisibility(View.GONE);
        }
        mTimeLineAdapter.notifyDataSetChanged();
    }

    private class TimeLineAdapter extends RecyclerView.Adapter {
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
