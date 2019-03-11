package cn.com.woong.readhub.ui.topic.topicdetail

import android.R.id
import cn.com.woong.readhub.utils.CommonUtils
import android.view.View.GONE
import cn.com.woong.readhub.bean.TopicTimeLineMo
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cn.com.woong.readhub.ui.widget.newsview.NewsViewHolder
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.text.method.TextKeyListener.clear
import cn.com.woong.readhub.bean.TopicDetailMo
import android.support.v4.view.ViewPager
import android.content.Intent.getIntent
import android.support.v7.widget.LinearLayoutManager
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import com.blankj.utilcode.util.BarUtils
import android.content.Intent
import cn.com.woong.readhub.bean.NewsDetailMo
import android.widget.ScrollView
import cn.com.woong.readhub.ui.widget.TitleBarLayout
import android.R.drawable.title_bar
import android.content.Context
import android.support.annotation.NonNull
import android.view.View
import cn.com.woong.readhub.R
import cn.com.woong.readhub.base.BaseActivity
import com.blankj.utilcode.util.TimeUtils
import java.text.SimpleDateFormat


class TopicDetailActivity : BaseActivity<TopicDetailPresenter>(), TopicDetailContract.View {
//    @BindView(R.id.title_bar)
//    internal var titleBar: TitleBarLayout? = null
//    @BindView(R.id.topic_detail_scrollview)
//    internal var topicDetailScrollView: ScrollView? = null
//    @BindView(R.id.tv_topic_title)
//    internal var tvTopicTitle: TextView? = null
//    @BindView(R.id.tv_topic_summary)
//    internal var tvTopicSummary: TextView? = null
//    @BindView(R.id.news_view_pager)
//    internal var newsViewPager: ViewPager? = null
//    @BindView(R.id.tv_news_indicator)
//    internal var tvNewsIndicator: TextView? = null
//    @BindView(R.id.tv_topic_timeline)
//    internal var tvTopicTimeLine: TextView? = null
//    @BindView(R.id.timeline_recycler_view)
//    internal var timelineRecyclerView: RecyclerView? = null
    private lateinit var mTopicId: String

//    private var mTimeLineAdapter: TimeLineAdapter? = null
//    private var mNewsViewPagetAdapter: NewsViewPagetAdapter? = null
//    private val mTopicTimeLineMos = ArrayList()
//    private val mNewsMos = ArrayList()
//    private val mNewsViewList = ArrayList()

//    protected val layoutId: Int
//        get() = R.layout.activity_topic_detail

    override fun getLayoutId(): Int {
        mPresenter = TopicDetailPresenter()
        return R.layout.activity_topic_detail
    }

    override fun initView() {
//        BarUtils.setStatusBarVisibility(this, true)
//        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.c1), 1)
//        titleBar!!.setTitle(getString(R.string.readhub_str))
//        titleBar!!.setTitleBarBgColor(getResources().getColor(R.color.c1))
//        titleBar!!.setTitleColor(getResources().getColor(R.color.b7))

//        titleBar!!.setLeftBack(object : View.OnClickListener() {
//            fun onClick(v: View) {
//                finish()
//            }
//        })

//        titleBar!!.setRightImage(R.drawable.ic_save_pic, object : View.OnClickListener() {
//            fun onClick(v: View) {
//                RxPermissions(this@TopicDetailActivity)
//                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        .subscribe(object : Consumer<Boolean>() {
//                            @Throws(Exception::class)
//                            fun accept(aBoolean: Boolean?) {
//                                if (aBoolean!!) {
//                                    ScreenShotUtil.getBitmapByView(this@TopicDetailActivity, topicDetailScrollView)
//                                }
//                            }
//                        })
//            }
//        })

//        mTimeLineAdapter = TimeLineAdapter()
//        timelineRecyclerView!!.layoutManager = LinearLayoutManager(this)
//        timelineRecyclerView!!.adapter = mTimeLineAdapter

//        mNewsViewPagetAdapter = NewsViewPagetAdapter()
//        newsViewPager!!.adapter = mNewsViewPagetAdapter
    }

    override fun initData() {
        mTopicId = intent.getStringExtra(TOPIC_ID)

//        if (mPresenter != null) {
//            mPresenter.getTopicDetail(mTopicId)
//        }
        mPresenter.getTopicDetail(mTopicId)

//        newsViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//
//            override fun onPageSelected(position: Int) {
//                tvNewsIndicator!!.setText(getString(R.string.see_more_news, position + 1, mNewsViewList.size()))
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {}
//        })
    }

    override fun updateTopicDetail(topicDetailMo: TopicDetailMo) {
//        tvTopicTitle!!.text = topicDetailMo.title
//        tvTopicSummary!!.text = topicDetailMo.summary
//
//        mTopicTimeLineMos.clear()
//        if (topicDetailMo.timeline != null && topicDetailMo.timeline.topics != null
//                && topicDetailMo.timeline.topics!!.size > 0) {
//            mTopicTimeLineMos.addAll(topicDetailMo.timeline.topics)
//        } else {
//            tvTopicTimeLine!!.visibility = View.GONE
//            timelineRecyclerView!!.visibility = View.GONE
//        }
//        mTimeLineAdapter!!.notifyDataSetChanged()
//
//        mNewsMos.clear()
//        mNewsViewList.clear()
//        if (topicDetailMo.newsArray != null && topicDetailMo.newsArray.size > 0) {
//            mNewsMos.addAll(topicDetailMo.newsArray)
//        }
//
//        if (mNewsMos.size() > 0) {
//            var i = 0
//            val count = mNewsMos.size()
//            while (i < count) {
//                val view = LayoutInflater.from(this).inflate(R.layout.item_news, null)
//                mNewsViewList.add(view)
//                i++
//            }
//
//            if (mNewsMos.size() > 1) {
//                tvNewsIndicator!!.visibility = View.VISIBLE
//                tvNewsIndicator!!.setText(getString(R.string.see_more_news, 1, mNewsMos.size()))
//            } else {
//                tvNewsIndicator!!.visibility = View.GONE
//            }
//        } else {
//            newsViewPager!!.visibility = View.GONE
//            tvNewsIndicator!!.visibility = View.GONE
//        }
//        mNewsViewPagetAdapter!!.notifyDataSetChanged()
    }

//    private inner class NewsViewPagetAdapter : PagerAdapter() {
//        override fun getCount(): Int {
//            return mNewsViewList.size()
//        }
//
//        override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
//            return view === `object`
//        }
//
//        override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
//            container.removeView(mNewsViewList.get(position))
//        }
//
//        @NonNull
//        override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
//            val viewHolder = NewsViewHolder(this@TopicDetailActivity, mNewsViewList.get(position))
//            viewHolder.bind(CommonUtils.changeFromNewsDetailMo(mNewsMos.get(position)))
//            container.addView(mNewsViewList.get(position))
//            return mNewsViewList.get(position)
//        }
//    }

//    inner class TimeLineAdapter : RecyclerView.Adapter<*>() {
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//            val view = getLayoutInflater().inflate(R.layout.item_topic_timeline, parent, false)
//            return TimeLineViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//            (holder as TimeLineViewHolder).onBind(position)
//        }
//
//        override fun getItemCount(): Int {
//            return mTopicTimeLineMos.size()
//        }
//    }

//    private inner class TimeLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val tvTimelineTime: TextView
//        private val tvTimelineLine: TextView
//        private val tvTimelineTitle: TextView
//
//        init {
//            tvTimelineTime = itemView.findViewById(R.id.tv_timeline_time)
//            tvTimelineLine = itemView.findViewById(R.id.tv_timeline_line)
//            tvTimelineTitle = itemView.findViewById(R.id.tv_timeline_title)
//        }
//
//        fun onBind(pos: Int) {
//            val topicTimeLineMo = mTopicTimeLineMos.get(pos)
//
//            if (pos >= mTopicTimeLineMos.size() - 1) {
//                tvTimelineLine.visibility = View.GONE
//            } else {
//                tvTimelineLine.visibility = View.VISIBLE
//            }
//
////            tvTimelineTime.setText(TimeUtils.millis2String(
////                    CommonUtils.getTimeStampByReadhubDateString(topicTimeLineMo.createdAt),
////                    SimpleDateFormat("yyyy/MM/dd")))
//
//            tvTimelineTitle.setText(topicTimeLineMo.title)
//
////            tvTimelineTitle.setOnClickListener(object : View.OnClickListener() {
////                fun onClick(v: View) {
////                    TopicDetailActivity.startTopicDetailActivity(this@TopicDetailActivity, topicTimeLineMo.id)
////                }
////            })
//        }
//    }

    companion object {
        private const val TOPIC_ID = "topic_id"

        fun startTopicDetailActivity(context: Context, topicId: String) {
            val intent = Intent(context, TopicDetailActivity::class.java)
            intent.putExtra(TOPIC_ID, topicId)
            context.startActivity(intent)
        }
    }
}
