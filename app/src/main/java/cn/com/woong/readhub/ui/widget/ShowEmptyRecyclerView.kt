package cn.com.woong.readhub.ui.widget

import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cn.com.woong.readhub.R
import kotlinx.android.synthetic.main.view_show_empty_recyclerview.view.*

class ShowEmptyRecyclerView @JvmOverloads constructor(@NonNull context: Context, @Nullable attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {
    private var emptyView: EmptyView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_show_empty_recyclerview, this)
    }

    fun getRecyclerView() : RecyclerView {
        return recycler_view
    }

    fun showEmptyView(show: Boolean, content: String) {
        if (show) {
            if (emptyView == null) {
                val view = view_stub_empty!!.inflate()
                emptyView = EmptyView(context, view)
            }

            recycler_view!!.visibility = GONE
            emptyView!!.setVisibility(View.VISIBLE)
            emptyView!!.setIvContent(R.drawable.ic_empty)
            emptyView!!.setTitle(content)
        } else {
            recycler_view!!.visibility = View.VISIBLE
            emptyView!!.setVisibility(GONE)
        }
    }
}