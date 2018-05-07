package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseFragment;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.db.DBManager;
import cn.com.woong.readhub.ui.topic.topicdetail.TopicDetailActivity;
import cn.com.woong.readhub.ui.widget.newsview.NewsView;
import cn.com.woong.readhub.utils.CommonUtils;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author woong
 *         Created by wong on 2018/3/9.
 */
public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.View {
    @BindView(R.id.topic_refresh_layout)
    EasyRefreshLayout topicRefreshLayout;
    @BindView(R.id.topic_recycler_view)
    RecyclerView topicRecyclerView;

    private String mLastOrder;
    private ArrayList<TopicMo> mTopicMos = new ArrayList<>();
    private TopicAdapter mTopicAdapter;

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
        mTopicAdapter = new TopicAdapter();
        topicRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topicRecyclerView.setAdapter(mTopicAdapter);
    }

    @Override
    protected void initData() {
        mLastOrder = "";
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
        if (TextUtils.isEmpty(order)) {
            mTopicMos.clear();
            mTopicMos.addAll(topicMos);
            mTopicAdapter.notifyDataSetChanged();
        } else {
            mTopicMos.addAll(topicMos);
            mTopicAdapter.notifyDataSetChanged();
        }
        mLastOrder = String.valueOf(topicMos.get(topicMos.size() - 1).order);
    }

    public class TopicAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_topic, parent, false);
            return new TopicViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TopicViewHolder) holder).onBind(position);
        }

        @Override
        public int getItemCount() {
            return mTopicMos.size();
        }
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        CardView topicCardView;
        TextView tvTopicTime;
        TextView tvTopicTitle;
        TextView tvTopicSummary;
        TextView tvNewsOne;
        TextView tvNewsTwo;
        ImageView ivTopicCollect;
        ImageView ivTopicShare;

        public TopicViewHolder(View itemView) {
            super(itemView);

            topicCardView = itemView.findViewById(R.id.topic_card_view);
            tvTopicTime = itemView.findViewById(R.id.tv_topic_time);
            tvTopicTitle = itemView.findViewById(R.id.tv_topic_title);
            tvTopicSummary = itemView.findViewById(R.id.tv_topic_summary);
            tvNewsOne = itemView.findViewById(R.id.tv_news_one);
            tvNewsTwo = itemView.findViewById(R.id.tv_news_two);
            ivTopicCollect = itemView.findViewById(R.id.iv_collect);
            ivTopicShare = itemView.findViewById(R.id.iv_share);
        }

        public void onBind(int pos) {
            final TopicMo topicMo = mTopicMos.get(pos);

            long publishDate = CommonUtils.getTimeStampByReadhubDateString(topicMo.publishDate);
            tvTopicTime.setText(TimeUtils.millis2String(publishDate, new SimpleDateFormat("MM-dd' 'HH:mm")));

            tvTopicTitle.setText(topicMo.title);
            tvTopicSummary.setText(topicMo.summary);

            if (topicMo.newsArray != null) {
                if (topicMo.newsArray.size() >= 2 ) {
                    if (!topicMo.newsArray.get(1).title.equals(topicMo.newsArray.get(0).title)) {
                        tvNewsOne.setVisibility(View.VISIBLE);
                        tvNewsOne.setText(topicMo.newsArray.get(1).title);
                    } else {
                        tvNewsOne.setVisibility(View.GONE);
                    }

                    if (topicMo.newsArray.size() > 2
                            &&!topicMo.newsArray.get(2).title.equals(topicMo.newsArray.get(0).title)
                            && !topicMo.newsArray.get(2).title.equals(topicMo.newsArray.get(1).title)) {
                        tvNewsTwo.setVisibility(View.VISIBLE);
                        tvNewsTwo.setText(topicMo.newsArray.get(2).title);
                    } else {
                        tvNewsTwo.setVisibility(View.GONE);
                    }
                } else {
                    tvNewsTwo.setVisibility(View.GONE);
                    tvNewsOne.setVisibility(View.GONE);
                }
            } else {
                tvNewsTwo.setVisibility(View.GONE);
                tvNewsOne.setVisibility(View.GONE);
            }

            topicCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    topicRefreshLayout.loadMoreComplete();
                    TopicDetailActivity.startTopicDetailActivity(getActivity(), topicMo.id);
                }
            });

            ivTopicCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showShort(R.string.add_read_delay);
                    DBManager.getInstance(getActivity()).insertTopicMo(topicMo);
                }
            });
        }
    }
}
