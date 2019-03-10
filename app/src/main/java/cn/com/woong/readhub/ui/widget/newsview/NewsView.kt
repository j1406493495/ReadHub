package cn.com.woong.readhub.ui.widget.newsview

import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import cn.com.woong.readhub.bean.NewsMo
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import cn.com.woong.readhub.R
import com.ajguan.library.EasyRefreshLayout
import kotlinx.android.synthetic.main.view_news.view.*

class NewsView @JvmOverloads constructor(@NonNull context: Context, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = -1)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var mNewsAdapter: NewsAdapter? = null
    private var mOnNewsListener: OnNewsListener? = null

    init {
        View.inflate(context, R.layout.view_news, this)

        mNewsAdapter = NewsAdapter(context)
        news_recycler_view.adapter = mNewsAdapter
        news_recycler_view.layoutManager = LinearLayoutManager(context)

        news_refresh_layout.addEasyEvent(object : EasyRefreshLayout.EasyEvent {
            override fun onLoadMore() {
                mOnNewsListener!!.onNewsLoadMore()
            }

            override fun onRefreshing() {
                mOnNewsListener!!.onNewsRefreshing()
            }
        })
    }

    fun updateNews(clear: Boolean, newsMos: ArrayList<NewsMo>) {
        mNewsAdapter!!.updateNews(clear, newsMos)
        news_refresh_layout.refreshComplete()
        news_refresh_layout.loadMoreComplete()
    }

    fun scrollToPosition(pos: Int) {
        news_recycler_view.scrollToPosition(pos)
    }

    fun loadMoreComplete() {
        news_refresh_layout.loadMoreComplete()
    }

    fun refreshComplete() {
        news_refresh_layout.refreshComplete()
    }

    interface OnNewsListener {
        fun onNewsLoadMore()
        fun onNewsRefreshing()
    }

    fun setOnNewsListener(listener: OnNewsListener) {
        mOnNewsListener = listener
    }
}


