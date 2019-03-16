package cn.com.woong.readhub

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.blankj.utilcode.util.ToastUtils
import cn.com.woong.readhub.ui.mine.MineFragment
import cn.com.woong.readhub.ui.news.NewsFragment
import cn.com.woong.readhub.ui.topic.TopicFragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import cn.com.woong.readhub.base.BaseFragment
import com.blankj.utilcode.util.BarUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mTopicFragment: TopicFragment? = null
    private var mNewsFragment: NewsFragment? = null
    private var mMineFragment: MineFragment? = null

    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private var exitTime: Long = 0
    private val mTitleStrs = arrayListOf<String>()
    private val mFragments = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        initFragment()

        BarUtils.setStatusBarVisibility(this, true)
        BarUtils.setStatusBarColor(this, resources.getColor(R.color.c1), 1)
        title_bar.setTitleBarBgColor(resources.getColor(R.color.c1))
        title_bar.setTitleColor(resources.getColor(R.color.b7))
    }

    private fun initData() {
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = mViewPagerAdapter
        view_pager.currentItem = 0
        rb_tab_topic.isChecked = true
        title_bar.setTitle(mTitleStrs.get(0))

        initListener()
    }

    private fun initListener() {
        rg_tab.setOnCheckedChangeListener { group, checkedId ->
            var pos = 0
            when (checkedId) {
                R.id.rb_tab_topic -> pos = 0
                R.id.rb_tab_news -> pos = 1
                R.id.rb_tab_mine -> pos = 2
                else -> { }
            }

            view_pager.currentItem = pos
            title_bar.setTitle(mTitleStrs.get(pos))
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                title_bar.setTitle(mTitleStrs.get(position))
                when (position) {
                    0 -> rb_tab_topic.isChecked = true
                    1 -> rb_tab_news.isChecked = true
                    2 -> rb_tab_mine.isChecked = true
                    else -> {}
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun initFragment() {
        mTopicFragment = TopicFragment()
        mNewsFragment = NewsFragment()
        mMineFragment = MineFragment()
        mFragments.add(mTopicFragment as Fragment)
        mFragments.add(mNewsFragment as Fragment)
        mFragments.add(mMineFragment as Fragment)
        mTitleStrs.add(getString(R.string.tab_hot))
        mTitleStrs.add(getString(R.string.tab_info))
        mTitleStrs.add(getString(R.string.tab_mine))
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 1000) {
            ToastUtils.showShort(R.string.exit_program)
            exitTime = System.currentTimeMillis()

            if (rb_tab_topic.isChecked) {
                mTopicFragment!!.refreshTopicData()
            } else if (rb_tab_news.isChecked) {
                mNewsFragment!!.refreshNewsData()
            }
        } else {
            finish()
            System.exit(0)
        }
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return mFragments.get(position)
        }

        override fun getCount(): Int {
            return mFragments.size
        }
    }
}
