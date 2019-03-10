package cn.com.woong.readhub.ui.widget

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.com.woong.readhub.R

class EmptyView {
    private var tvTitle: TextView? = null
    private var tvContent: TextView? = null
    private var ivContent: ImageView? = null
    private var parentView: View? = null

    constructor(context: Context, view: View) {
        parentView = view

        tvTitle = view.findViewById(R.id.tv_title)
        tvContent = view.findViewById(R.id.tv_content)
        ivContent = view.findViewById(R.id.iv_content) as ImageView
    }

    fun setVisibility(visibility: Int) {
        parentView?.visibility = visibility
    }

    fun setTitle(title: String) {
        tvTitle?.text = title
    }

    fun setContent(content: String) {
        tvContent?.text = content
    }

    fun setIvContent(res: Int) {
        ivContent?.setImageResource(res)
    }
}