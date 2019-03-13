package cn.com.woong.readhub.ui.topic.topicdetail

import android.Manifest
import cn.com.woong.readhub.bean.TopicDetailMo
import com.blankj.utilcode.util.BarUtils
import android.content.Intent
import android.content.Context
import android.support.annotation.NonNull
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.com.woong.readhub.R
import cn.com.woong.readhub.base.BaseActivity
import cn.com.woong.readhub.bean.NewsDetailMo
import cn.com.woong.readhub.bean.TopicTimeLineMo
import cn.com.woong.readhub.ui.widget.newsview.NewsViewHolder
import cn.com.woong.readhub.utils.CommonUtils
import cn.com.woong.readhub.utils.ScreenShotUtils
import com.blankj.utilcode.util.TimeUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_topic_detail.*
import java.text.SimpleDateFormat

class TopicDetailActivity : BaseActivity<TopicDetailPresenter>(), TopicDetailContract.View {
    private lateinit var mTopicId: String

    private var mTimeLineAdapter: TimeLineAdapter? = null
    private var mNewsViewPagetAdapter: NewsViewPagetAdapter? = null
    private val mTopicTimeLineMos = ArrayList<TopicTimeLineMo>()
    private val mNewsMos = arrayListOf<NewsDetailMo>()
    private val mNewsViewList = arrayListOf<View>()

    override fun getLayoutId(): Int {
        mPresenter = TopicDetailPresenter()
        return R.layout.activity_topic_detail
    }

    override fun initView() {
        BarUtils.setStatusBarVisibility(this, true)
        BarUtils.setStatusBarColor(this, resources.getColor(R.color.c1), 1)
        title_bar.setTitle(getString(R.string.readhub_str))
        title_bar.setTitleBarBgColor(resources.getColor(R.color.c1))
        title_bar.setTitleColor(resources.getColor(R.color.b7))

        title_bar.setLeftBack(View.OnClickListener { finish() })

        title_bar.setRightImage(R.drawable.ic_save_pic, View.OnClickListener {
            RxPermissions(this@TopicDetailActivity)
                    .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe {
                        if (it) {
                            ScreenShotUtils.getBitmapByView(this@TopicDetailActivity, topic_detail_scrollview)
                        }
                    }
        })

        mTimeLineAdapter = TimeLineAdapter()
        timeline_recycler_view.layoutManager = LinearLayoutManager(this)
        timeline_recycler_view.adapter = mTimeLineAdapter

        mNewsViewPagetAdapter = NewsViewPagetAdapter()
        news_view_pager.adapter = mNewsViewPagetAdapter
    }

    override fun initData() {
        mTopicId = intent.getStringExtra(TOPIC_ID)

        mPresenter.getTopicDetail(mTopicId)

        news_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                tv_news_indicator.text = getString(R.string.see_more_news, position + 1, mNewsViewList.size)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun updateTopicDetail(topicDetailMo: TopicDetailMo) {
        tv_topic_title.text = topicDetailMo.title
        tv_topic_summary.text = topicDetailMo.summary

        mTopicTimeLineMos.clear()
        if (topicDetailMo.timeline != null && topicDetailMo.timeline.topics != null
                && topicDetailMo.timeline.topics!!.size > 0) {
            mTopicTimeLineMos.addAll(topicDetailMo.timeline.topics)
        } else {
            tv_topic_timeline.visibility = View.GONE
            timeline_recycler_view.visibility = View.GONE
        }
        mTimeLineAdapter!!.notifyDataSetChanged()

        mNewsMos.clear()
        mNewsViewList.clear()
        if (topicDetailMo.newsArray != null && topicDetailMo.newsArray.size > 0) {
            mNewsMos.addAll(topicDetailMo.newsArray)
        }

        if (mNewsMos.size > 0) {
            var i = 0
            val count = mNewsMos.size
            while (i < count) {
                val view = LayoutInflater.from(this).inflate(R.layout.item_news, null)
                mNewsViewList.add(view)
                i++
            }

            if (mNewsMos.size > 1) {
                tv_news_indicator.visibility = View.VISIBLE
                tv_news_indicator.text = getString(R.string.see_more_news, 1, mNewsMos.size)
            } else {
                tv_news_indicator.visibility = View.GONE
            }
        } else {
            news_view_pager.visibility = View.GONE
            tv_news_indicator.visibility = View.GONE
        }
        mNewsViewPagetAdapter!!.notifyDataSetChanged()
    }

    private inner class NewsViewPagetAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return mNewsViewList.size
        }

        override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
            container.removeView(mNewsViewList.get(position))
        }

        @NonNull
        override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
            val viewHolder = NewsViewHolder(this@TopicDetailActivity, mNewsViewList.get(position))
            viewHolder.bind(CommonUtils.changeFromNewsDetailMo(mNewsMos.get(position)))
            container.addView(mNewsViewList.get(position))
            return mNewsViewList.get(position)
        }
    }

    inner class TimeLineAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = layoutInflater.inflate(R.layout.item_topic_timeline, parent, false)
            return TimeLineViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as TimeLineViewHolder).onBind(position)
        }

        override fun getItemCount(): Int {
            return mTopicTimeLineMos.size
        }
    }

    private inner class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTimelineTime: TextView
        private val tvTimelineLine: TextView
        private val tvTimelineTitle: TextView

        init {
            tvTimelineTime = itemView.findViewById(R.id.tv_timeline_time)
            tvTimelineLine = itemView.findViewById(R.id.tv_timeline_line)
            tvTimelineTitle = itemView.findViewById(R.id.tv_timeline_title)
        }

        fun onBind(pos: Int) {
            val topicTimeLineMo = mTopicTimeLineMos[pos]

            if (pos >= mTopicTimeLineMos.size - 1) {
                tvTimelineLine.visibility = View.GONE
            } else {
                tvTimelineLine.visibility = View.VISIBLE
            }

            tvTimelineTime.text = TimeUtils.millis2String(
                    CommonUtils.getTimeStampByReadhubDateString(topicTimeLineMo.createdAt),
                    SimpleDateFormat("yyyy/MM/dd"))

            tvTimelineTitle.text = topicTimeLineMo.title

            tvTimelineTitle.setOnClickListener {
                TopicDetailActivity.startTopicDetailActivity(this@TopicDetailActivity, topicTimeLineMo.id)
            }
        }
    }

    companion object {
        private const val TOPIC_ID = "topic_id"

        fun startTopicDetailActivity(context: Context, topicId: String) {
            val intent = Intent(context, TopicDetailActivity::class.java)
            intent.putExtra(TOPIC_ID, topicId)
            context.startActivity(intent)
        }
    }
}
