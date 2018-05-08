package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.TopicMo;

/**
 * Created by wong on 2018/5/8.
 */

public class TopicAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<TopicMo> mTopicMos = new ArrayList<>();

    public TopicAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TopicViewHolder) holder).onBind(mTopicMos.get(position));
    }

    @Override
    public int getItemCount() {
        return mTopicMos.size();
    }

    public void updateTopics(boolean clear, ArrayList<TopicMo> topicMos) {
        if (clear) {
            mTopicMos.clear();
        }

        mTopicMos.addAll(topicMos);
        notifyDataSetChanged();
    }
}
