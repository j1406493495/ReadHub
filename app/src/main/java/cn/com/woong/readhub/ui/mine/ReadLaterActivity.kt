package cn.com.woong.readhub.ui.mine

import android.view.ViewGroup
import android.support.v4.view.PagerAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import android.support.v7.widget.LinearLayoutManager
import cn.com.woong.readhub.db.DBManager
import cn.com.woong.readhub.bean.NewsMo
import cn.com.woong.readhub.bean.TopicMo
import com.blankj.utilcode.util.BarUtils
import android.content.Intent
import android.app.Activity
import android.os.Bundle
import cn.com.woong.readhub.ui.widget.newsview.NewsAdapter
import cn.com.woong.readhub.ui.widget.ShowEmptyRecyclerView
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.View
import cn.com.woong.readhub.R
import cn.com.woong.readhub.eventbus.Event
import cn.com.woong.readhub.ui.topic.TopicAdapter
import kotlinx.android.synthetic.main.activity_readlater.*

class ReadLaterActivity : AppCompatActivity() {
    private var mNewsRecycler: ShowEmptyRecyclerView? = null
    private var mTopicRecycler: ShowEmptyRecyclerView? = null
    private var mTopicAdapter: TopicAdapter? = null
    private var mNewsAdapter: NewsAdapter? = null

    private var mViewPagerAdapter: ViewPagerAdapter? = null
    private var mReadLaterViews = arrayListOf<ShowEmptyRecyclerView>()
    private var mTopicMos: ArrayList<TopicMo> = ArrayList()
    private var mNewsMos: ArrayList<NewsMo> = ArrayList()
    private val mTabTitle = arrayOf("热门", "资讯")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readlater)
        initView()
        initData()
    }

    private fun initView() {
        BarUtils.setStatusBarColor(this, resources.getColor(R.color.c1), 1)
        title_bar.setTitleBarBgColor(resources.getColor(R.color.c1))
        title_bar.setTitleColor(resources.getColor(R.color.b7))

        title_bar.setTitle(getString(R.string.read_later))
        title_bar.setOnClickListener { finish() }

        mTopicRecycler = ShowEmptyRecyclerView(this)
        mNewsRecycler = ShowEmptyRecyclerView(this)

        mReadLaterViews.clear()
        mReadLaterViews.add(mTopicRecycler as ShowEmptyRecyclerView)
        mReadLaterViews.add(mNewsRecycler as ShowEmptyRecyclerView)
    }

    private fun initData() {
        EventBus.getDefault().register(this)

        mViewPagerAdapter = ViewPagerAdapter()
        readlater_view_pager!!.adapter = mViewPagerAdapter
        readlater_tab_layout!!.setupWithViewPager(readlater_view_pager)

        mTopicMos = DBManager.getInstance(this).queryAllTopicMo() as ArrayList<TopicMo>
        mNewsMos = DBManager.getInstance(this).queryAllNewsMo() as ArrayList<NewsMo>

        mTopicAdapter?.showDelete(true)
        mTopicRecycler?.getRecyclerView()?.setAdapter(mTopicAdapter)
        mTopicRecycler?.getRecyclerView()?.setLayoutManager(LinearLayoutManager(this))
        mTopicAdapter?.updateTopics(true, mTopicMos)

        mNewsAdapter?.showDelete(true)
        mNewsRecycler?.getRecyclerView()?.setAdapter(mNewsAdapter)
        mNewsRecycler?.getRecyclerView()?.setLayoutManager(LinearLayoutManager(this))
        mNewsAdapter?.updateNews(true, mNewsMos)

        if (mTopicMos.size === 0) {
            mTopicRecycler!!.showEmptyView(true, getString(R.string.no_readlater))
        }

        if (mNewsMos.size === 0) {
            mNewsRecycler!!.showEmptyView(true, getString(R.string.no_readlater))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun removeTopicEvent(event: Event.ReadLaterTopicRemoveEvent) {
        mTopicAdapter!!.removeTopic(event.position)

        mTopicMos.removeAt(event.position)
        if (mTopicMos.size === 0) {
            mTopicRecycler!!.showEmptyView(true, getString(R.string.no_readlater))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun removeNewsEvent(event: Event.ReadLaterNewsRemoveEvent) {
        mNewsAdapter!!.removeNews(event.position)

        mNewsMos.removeAt(event.position)
        if (mNewsMos.size === 0) {
            mNewsRecycler!!.showEmptyView(true, getString(R.string.no_readlater))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    inner class ViewPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return mReadLaterViews.size
        }

        override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(mReadLaterViews.get(position))
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mReadLaterViews.get(position))
            return mReadLaterViews.get(position)
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence? {
            return mTabTitle[position]
        }
    }

    companion object {
        fun startReadLaterActivity(activity: Activity) {
            val intent = Intent(activity, ReadLaterActivity::class.java)
            activity.startActivity(intent)
        }
    }
}