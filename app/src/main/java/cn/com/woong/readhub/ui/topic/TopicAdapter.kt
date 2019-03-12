package cn.com.woong.readhub.ui.topic

import android.content.Context
import cn.com.woong.readhub.db.DBManager
import cn.com.woong.readhub.bean.TopicMo
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.woong.readhub.R


class TopicAdapter(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mTopicMos = arrayListOf<TopicMo>()
    private var mShowDelete: Boolean = false

    fun showDelete(show: Boolean) {
        mShowDelete = show
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(mContext, view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mShowDelete) {
            (holder as TopicViewHolder).showDelete()
        }

        (holder as TopicViewHolder).onBind(mTopicMos.get(position))
    }

    override fun getItemCount(): Int {
        return mTopicMos.size
    }

    fun updateTopics(clear: Boolean, topicMos: ArrayList<TopicMo>) {
        if (clear) {
            mTopicMos.clear()
        }

        mTopicMos.addAll(topicMos)
        notifyDataSetChanged()
    }

    fun removeTopic(position: Int) {
        DBManager.getInstance(mContext).deleteTopicMo(mTopicMos.get(position))
        mTopicMos.removeAt(position)
        notifyItemRemoved(position)
    }
}
