package cn.com.woong.readhub.ui.news

import android.view.ViewGroup
import android.support.v4.view.PagerAdapter
import android.text.method.TextKeyListener.clear
import android.text.TextUtils
import cn.com.woong.readhub.utils.CommonUtils
import cn.com.woong.readhub.bean.NewsMo
import cn.com.woong.readhub.ui.widget.newsview.NewsView
import android.support.design.widget.TabLayout
import android.R
import android.support.v4.view.ViewPager


class NewsFragment : BaseDaggerFragment<NewsPresenter>(), NewsContract.View {
    @BindView(R.id.news_tab_layout)
    internal var newsTabLayout: TabLayout? = null
    @BindView(R.id.news_view_pager)
    internal var newsViewPager: ViewPager? = null

    private var mNewsViewPagerAdapter: NewsViewPagerAdapter? = null
    private val mNewsViewList = ArrayList()
    private val mPublishDateList = ArrayList()
    private val mTabTitle = arrayOf("科技动态", "开发者资讯", "区块链快讯")
    private var mCurrentTabPos = 0

    protected val layoutId: Int
        get() = R.layout.fragment_news

    protected fun initView(view: View) {
        initViewPager()
    }

    protected fun initData() {
        if (mPresenter != null) {
            mPresenter.getTechNews("")
        }

        newsTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mCurrentTabPos = tab.position
                if (mPresenter != null) {
                    when (tab.position) {
                        0 -> mPresenter.getTechNews("")
                        1 -> mPresenter.getDevelopNews("")
                        2 -> mPresenter.getBlockchainNews("")
                        else -> {
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initViewPager() {
        var i = 0
        val count = 3
        while (i < count) {
            val pos = i
            val newsView = NewsView(getActivity())
            newsView.setOnNewsListener(object : NewsView.OnNewsListener {
                override fun onNewsLoadMore() {
                    if (mPresenter != null) {
                        when (pos) {
                            0 -> mPresenter.getTechNews(mPublishDateList.get(0))
                            1 -> mPresenter.getDevelopNews(mPublishDateList.get(1))
                            2 -> mPresenter.getBlockchainNews(mPublishDateList.get(2))
                            else -> {
                            }
                        }
                    }
                }

                override fun onNewsRefreshing() {
                    if (mPresenter != null) {
                        mPublishDateList.set(pos, "")
                        when (pos) {
                            0 -> mPresenter.getTechNews("")
                            1 -> mPresenter.getDevelopNews("")
                            2 -> mPresenter.getBlockchainNews("")
                            else -> {
                            }
                        }
                    }
                }
            })
            mNewsViewList.add(newsView)
            mPublishDateList.add("")
            i++
        }

        mNewsViewPagerAdapter = NewsViewPagerAdapter()
        newsViewPager!!.adapter = mNewsViewPagerAdapter
        newsTabLayout!!.setupWithViewPager(newsViewPager)
    }

    fun onResume() {
        super.onResume()
        for (newsView in mNewsViewList) {
            newsView.loadMoreComplete()
        }
    }

    fun refreshNewsData() {
        if (mPresenter != null) {
            mPublishDateList.set(mCurrentTabPos, "")
            mNewsViewList.get(mCurrentTabPos).scrollToPosition(0)
            when (mCurrentTabPos) {
                0 -> mPresenter.getTechNews("")
                1 -> mPresenter.getDevelopNews("")
                2 -> mPresenter.getBlockchainNews("")
                else -> {
                }
            }
        }
    }

    fun updateTechNews(publishDate: String, newsMos: ArrayList<NewsMo>) {
        mPublishDateList.set(0, CommonUtils.getTimeStampByReadhubDateString(
                newsMos[newsMos.size() - 1].publishDate).toString())
        mNewsViewList.get(0).updateNews(TextUtils.isEmpty(publishDate), newsMos)
    }

    fun updateDevelopNews(publishDate: String, newsMos: ArrayList<NewsMo>) {
        mPublishDateList.set(1, CommonUtils.getTimeStampByReadhubDateString(
                newsMos[newsMos.size() - 1].publishDate).toString())
        mNewsViewList.get(1).updateNews(TextUtils.isEmpty(publishDate), newsMos)
    }

    fun updateBlockchainNews(publishDate: String, newsMos: ArrayList<NewsMo>) {
        mPublishDateList.set(2, CommonUtils.getTimeStampByReadhubDateString(
                newsMos[newsMos.size() - 1].publishDate).toString())
        mNewsViewList.get(2).updateNews(TextUtils.isEmpty(publishDate), newsMos)
    }

    fun onDestroy() {
        super.onDestroy()
        mNewsViewList.clear()
        mPublishDateList.clear()
    }

    private inner class NewsViewPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return mNewsViewList.size()
        }

        @NonNull
        override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
            container.addView(mNewsViewList.get(position))
            return mNewsViewList.get(position)
        }

        override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
            container.removeView(mNewsViewList.get(position))
        }

        fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
            return view === `object`
        }

        @Nullable
        override fun getPageTitle(position: Int): CharSequence? {
            return mTabTitle[position]
        }
    }
}
