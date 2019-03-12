package cn.com.woong.readhub.ui.topic

import android.content.Context
import org.greenrobot.eventbus.EventBus
import cn.com.woong.readhub.db.DBManager
import com.blankj.utilcode.util.ToastUtils
import cn.com.woong.readhub.ui.topic.topicdetail.TopicDetailActivity
import cn.com.woong.readhub.utils.CommonUtils
import cn.com.woong.readhub.bean.TopicMo
import android.widget.TextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import cn.com.woong.readhub.R
import cn.com.woong.readhub.eventbus.Event
import com.blankj.utilcode.util.TimeUtils
import java.text.SimpleDateFormat


class TopicViewHolder(private val mContext: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var topicCardView: CardView
    private var tvTopicTime: TextView
    private var tvTopicTitle: TextView
    private var tvTopicSummary: TextView
    private var tvNewsOne: TextView
    private var tvNewsTwo: TextView
    private var ivTopicCollect: ImageView
    private var ivTopicDelete: ImageView

    init {
        topicCardView = itemView.findViewById(R.id.topic_card_view)
        tvTopicTime = itemView.findViewById(R.id.tv_topic_time)
        tvTopicTitle = itemView.findViewById(R.id.tv_topic_title)
        tvTopicSummary = itemView.findViewById(R.id.tv_topic_summary)
        tvNewsOne = itemView.findViewById(R.id.tv_news_one)
        tvNewsTwo = itemView.findViewById(R.id.tv_news_two)
        ivTopicCollect = itemView.findViewById(R.id.iv_collect)
        ivTopicDelete = itemView.findViewById(R.id.iv_delete)
    }

    fun showDelete() {
        ivTopicDelete.visibility = View.VISIBLE
        ivTopicCollect.visibility = View.GONE
    }

    fun onBind(topicMo: TopicMo) {
        val publishDate = CommonUtils.getTimeStampByReadhubDateString(topicMo.publishDate)!!
        tvTopicTime.text = TimeUtils.millis2String(publishDate, SimpleDateFormat("MM-dd' 'HH:mm"))

        tvTopicTitle.text = topicMo.title
        tvTopicSummary.text = topicMo.summary

        topicCardView.setOnClickListener {
            TopicDetailActivity.startTopicDetailActivity(mContext, topicMo.id)
        }

        ivTopicCollect.setOnClickListener {
            ToastUtils.showShort(R.string.add_read_delay)
            DBManager.getInstance(mContext).insertTopicMo(topicMo)
        }

        ivTopicDelete.setOnClickListener {
            val event = Event.ReadLaterTopicRemoveEvent()
            event.position = layoutPosition
            EventBus.getDefault().post(event)
        }
    }
}
