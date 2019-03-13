package cn.com.woong.readhub.ui.news

import android.content.Context
import android.view.ViewGroup
import android.support.v4.view.PagerAdapter
import android.text.TextUtils
import cn.com.woong.readhub.utils.CommonUtils
import cn.com.woong.readhub.bean.NewsMo
import cn.com.woong.readhub.ui.widget.newsview.NewsView
import android.support.design.widget.TabLayout
import android.view.View
import cn.com.woong.readhub.R
import cn.com.woong.readhub.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<NewsPresenter>(), NewsContract.View {
    private var mNewsViewPagerAdapter: NewsViewPagerAdapter? = null
    private val mNewsViewList = arrayListOf<NewsView>()
    private val mPublishDateList = arrayListOf<String>()
    private val mTabTitle = arrayOf("科技动态", "开发者资讯", "区块链快讯")
    private var mCurrentTabPos = 0

    override fun getLayoutId(): Int {
        mPresenter = NewsPresenter()
        return R.layout.fragment_news
    }

    override fun initView(view: View?) {
        initViewPager()
    }

    override fun initData() {
        mPresenter?.getTechNews("")

        news_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mCurrentTabPos = tab.position

                mPresenter?.let {
                    when (tab.position) {
                        0 -> mPresenter.getTechNews("")
                        1 -> mPresenter.getDevelopNews("")
                        2 -> mPresenter.getBlockchainNews("")
                        else -> { }
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
            val newsView = NewsView(activity as Context)
            newsView.setOnNewsListener(object : NewsView.OnNewsListener {
                override fun onNewsLoadMore() {
                    mPresenter?.let {
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
                    mPresenter?.let {
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
        news_view_pager.adapter = mNewsViewPagerAdapter
        news_tab_layout.setupWithViewPager(news_view_pager)
    }

    override fun onResume() {
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

    override fun updateTechNews(publishDate: String, newsMos: ArrayList<NewsMo>) {
        mPublishDateList.set(0, CommonUtils.getTimeStampByReadhubDateString(
                newsMos[newsMos.size - 1].publishDate).toString())
        mNewsViewList.get(0).updateNews(TextUtils.isEmpty(publishDate), newsMos)
    }

    override fun updateDevelopNews(publishDate: String, newsMos: ArrayList<NewsMo>) {
        mPublishDateList.set(1, CommonUtils.getTimeStampByReadhubDateString(
                newsMos[newsMos.size - 1].publishDate).toString())
        mNewsViewList.get(1).updateNews(TextUtils.isEmpty(publishDate), newsMos)
    }

    override fun updateBlockchainNews(publishDate: String, newsMos: ArrayList<NewsMo>) {
        mPublishDateList.set(2, CommonUtils.getTimeStampByReadhubDateString(
                newsMos[newsMos.size - 1].publishDate).toString())
        mNewsViewList.get(2).updateNews(TextUtils.isEmpty(publishDate), newsMos)
    }

    override fun onDestroy() {
        super.onDestroy()
        mNewsViewList.clear()
        mPublishDateList.clear()
    }

    private inner class NewsViewPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return mNewsViewList.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mNewsViewList.get(position))
            return mNewsViewList.get(position)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(mNewsViewList.get(position))
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTabTitle[position]
        }
    }
}
