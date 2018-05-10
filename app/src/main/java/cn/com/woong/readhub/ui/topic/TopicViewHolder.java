package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;

import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.db.DBManager;
import cn.com.woong.readhub.eventbus.Event;
import cn.com.woong.readhub.ui.topic.topicdetail.TopicDetailActivity;
import cn.com.woong.readhub.utils.CommonUtils;
import cn.com.woong.readhub.utils.ScreenShotUtil;

/**
 * Created by wong on 2018/5/8.
 */

public class TopicViewHolder extends RecyclerView.ViewHolder {
    CardView topicCardView;
    TextView tvTopicTime;
    TextView tvTopicTitle;
    TextView tvTopicSummary;
    TextView tvNewsOne;
    TextView tvNewsTwo;
    ImageView ivTopicCollect;
    ImageView ivTopicDelete;

    private Context mContext;

    public TopicViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;

        topicCardView = itemView.findViewById(R.id.topic_card_view);
        tvTopicTime = itemView.findViewById(R.id.tv_topic_time);
        tvTopicTitle = itemView.findViewById(R.id.tv_topic_title);
        tvTopicSummary = itemView.findViewById(R.id.tv_topic_summary);
        tvNewsOne = itemView.findViewById(R.id.tv_news_one);
        tvNewsTwo = itemView.findViewById(R.id.tv_news_two);
        ivTopicCollect = itemView.findViewById(R.id.iv_collect);
        ivTopicDelete = itemView.findViewById(R.id.iv_delete);
    }

    public void showDelete() {
        ivTopicDelete.setVisibility(View.VISIBLE);
        ivTopicCollect.setVisibility(View.GONE);
    }

    public void onBind(final TopicMo topicMo) {
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
                TopicDetailActivity.startTopicDetailActivity(mContext, topicMo.id);
            }
        });

        ivTopicCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(R.string.add_read_delay);
                DBManager.getInstance(mContext).insertTopicMo(topicMo);
            }
        });

        ivTopicDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event.ReadLaterTopicRemoveEvent event = new Event.ReadLaterTopicRemoveEvent();
                event.position = getLayoutPosition();
                EventBus.getDefault().post(event);
            }
        });
    }
}
