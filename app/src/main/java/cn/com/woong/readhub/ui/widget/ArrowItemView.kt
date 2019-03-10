package cn.com.woong.readhub.ui.widget

import android.content.Context
import android.view.LayoutInflater
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import cn.com.woong.readhub.R
import kotlinx.android.synthetic.main.view_arrow_item.view.*

class ArrowItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {
    /**
     * arrow item 事件监听
     */
    private var mOnArrowListener: OnArrowListener? = null

    init {
        init()

        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ArrowItemView)
        tv_arrow_content.text = typedArray.getString(R.styleable.ArrowItemView_arrowContent)
        iv_arrow_label.setImageResource(typedArray.getResourceId(R.styleable.ArrowItemView_arrowLabel, R.drawable.ic_info))

        val labelVisible = typedArray.getBoolean(R.styleable.ArrowItemView_arrowLabelVisible, true)
        iv_arrow_label.visibility = if (labelVisible) View.VISIBLE else GONE

        val rightVisible = typedArray.getBoolean(R.styleable.ArrowItemView_arrowRightVisible, true)
        iv_arrow_right.visibility = if (rightVisible) View.VISIBLE else GONE

        typedArray.recycle()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_arrow_item, this)

        switch_button.setOnCheckedChangeListener { _, isChecked ->
            if (mOnArrowListener != null) {
                mOnArrowListener!!.onSwitchChange(isChecked)
            }
        }
    }

    fun setArrowContent(content: String) {
        tv_arrow_content.text = content
    }

    fun setArrowLabelVisible(visible: Boolean) {
        iv_arrow_label.visibility = if (visible) View.VISIBLE else GONE
    }

    fun setArrowRightVisible(visible: Boolean) {
        iv_arrow_right.visibility = if (visible) View.VISIBLE else GONE
    }

    fun setSwitchButtonVisible(visible: Boolean) {
        switch_button.visibility = if (visible) View.VISIBLE else GONE
    }

    fun setOnArrowListener(listener: OnArrowListener) {
        mOnArrowListener = listener
    }

    interface OnArrowListener {
        fun onSwitchChange(checkable: Boolean)
    }
}