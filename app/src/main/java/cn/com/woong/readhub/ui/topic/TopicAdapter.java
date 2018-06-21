package cn.com.woong.readhub.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.com.woong.readhub.R;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.db.DBManager;

/**
 * Created by wong on 2018/5/8.
 */

public class TopicAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<TopicMo> mTopicMos = new ArrayList<>();
    private boolean mShowDelete;

    public TopicAdapter(Context context) {
        mContext = context;
    }

    public void showDelete(boolean show) {
        mShowDelete = show;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mShowDelete) {
            ((TopicViewHolder) holder).showDelete();
        }

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

    public void removeTopic(int position) {
        DBManager.getInstance(mContext).deleteTopicMo(mTopicMos.get(position));
        mTopicMos.remove(position);
        notifyItemRemoved(position);

    }
}
