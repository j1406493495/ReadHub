package cn.com.woong.readhub.ui.widget.newsview

import com.blankj.utilcode.util.ToastUtils
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import org.greenrobot.eventbus.EventBus
import cn.com.woong.readhub.db.DBManager
import cn.com.woong.readhub.ui.WebActivity
import cn.com.woong.readhub.utils.CommonUtils
import cn.com.woong.readhub.bean.NewsMo
import android.widget.TextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import cn.com.woong.readhub.R
import cn.com.woong.readhub.eventbus.Event
import com.blankj.utilcode.util.TimeUtils
import java.text.SimpleDateFormat


class NewsViewHolder(private val mContext: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val newsCardView: CardView
    private val tvItemTitle: TextView
    private val tvItemTime: TextView
    private val tvItemAuthor: TextView
    private val tvItemContent: TextView
    private val ivCollect: ImageView
    private val ivShare: ImageView
    private val ivDelete: ImageView

    init {
        newsCardView = itemView.findViewById(R.id.news_card_view)
        tvItemTitle = itemView.findViewById(R.id.tv_item_title)
        tvItemContent = itemView.findViewById(R.id.tv_item_content)
        tvItemAuthor = itemView.findViewById(R.id.tv_item_author)
        tvItemTime = itemView.findViewById(R.id.tv_item_time)
        ivCollect = itemView.findViewById(R.id.iv_collect)
        ivShare = itemView.findViewById(R.id.iv_share)
        ivDelete = itemView.findViewById(R.id.iv_delete)
    }

    fun showDelete() {
        ivDelete.visibility = View.VISIBLE
        ivCollect.visibility = View.GONE
    }

    fun bind(newsMo: NewsMo) {
        tvItemTitle.text = newsMo.title
        tvItemContent.text = newsMo.summary
        tvItemAuthor.text = newsMo.siteName + "/" + newsMo.authorName

        val publishDate = CommonUtils.getTimeStampByReadhubDateString(newsMo.publishDate)!!
        tvItemTime.text = TimeUtils.millis2String(publishDate, SimpleDateFormat("MM-dd HH:mm"))

        newsCardView.setOnClickListener {
            WebActivity.startWebActivity(mContext, newsMo.mobileUrl)
        }

        ivCollect.setOnClickListener {
            ToastUtils.showShort(R.string.add_read_delay)
            DBManager.getInstance(mContext).insertNewsMo(newsMo)
        }

        ivDelete.setOnClickListener {
            val event = Event.ReadLaterNewsRemoveEvent()
            event.position = layoutPosition
            EventBus.getDefault().post(event)
        }

        ivShare.setOnClickListener {
            val clipboardManager = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("ShareUrl", newsMo.mobileUrl)

            clipboardManager.let {
                clipboardManager.primaryClip = mClipData
                ToastUtils.showShort(R.string.copy_url)
            }
        }
    }
}
