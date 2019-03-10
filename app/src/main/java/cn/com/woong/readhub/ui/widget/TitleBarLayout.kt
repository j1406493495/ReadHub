package cn.com.woong.readhub.ui.widget

import android.content.Context
import android.support.annotation.ColorInt
import android.widget.TextView
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import cn.com.woong.readhub.R
import kotlinx.android.synthetic.main.view_title_bar.view.*

class TitleBarLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = -1)
    : LinearLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.view_title_bar, this)
    }

    /**
     * 设置背景色
     */
    fun setTitleBarBgColor(@ColorInt color: Int) {
        rl_actionbar.setBackgroundColor(color)
    }

    fun setTitleBarBgDrawable(drawable: Drawable) {
        rl_actionbar.background = drawable
    }

    /**
     * 用来设置页面标题
     */
    fun setTitle(title: String) {
        tv_center.text = title
        tv_center.visibility = View.VISIBLE
    }

    fun setTitle(text: CharSequence, type: TextView.BufferType) {
        tv_center.setText(text, type)
        tv_center.visibility = View.VISIBLE
    }

    fun setTitleColor(@ColorInt color: Int) {
        tv_center.setTextColor(color)
    }

    /**
     * 设置左侧为正常返回键，常用
     */
    fun setLeftBack(onClickListener: View.OnClickListener) {
        setLeftImage(R.drawable.ic_common_back, onClickListener)
    }

    /**
     * 用于设置左侧图片按钮，如果是正常返回键使用setLeftBack()
     *
     * @param imgRes          imgRes
     * @param onClickListener onClickListenerlibsocketio.so
     */
    fun setLeftImage(imgRes: Int, onClickListener: View.OnClickListener?) {
        // 隐藏显示
        if (imgRes <= 0 && onClickListener == null) {
            iv_left.setImageBitmap(null)
            iv_left.setOnClickListener(null)
            iv_left.visibility = View.GONE
        } else {
            iv_left.setImageResource(imgRes)
            iv_left.setOnClickListener(onClickListener)
            iv_left.visibility = View.VISIBLE
        }
    }

    /**
     * 设置左侧为文本按钮
     *
     * @param btnText         btnText
     * @param onClickListener onClickListener
     */
    fun setLeftText(btnText: String, onClickListener: View.OnClickListener) {
        tv_left.text = btnText
        tv_left.setOnClickListener(onClickListener)
        tv_left.visibility = View.VISIBLE
    }

    fun setLeftTextColor(@ColorInt color: Int) {
        tv_left.setTextColor(color)
    }

    /**
     * 设置右侧为图片按钮
     *
     * @param imgRes          imgRes
     * @param onClickListener onClickListener
     */
    fun setRightImage(imgRes: Int, onClickListener: View.OnClickListener?) {
        // 隐藏显示
        if (imgRes <= 0 && onClickListener == null) {
            iv_right.setImageBitmap(null)
            iv_right.setOnClickListener(null)
            iv_right.visibility = View.GONE
        } else {
            iv_right.setImageResource(imgRes)
            iv_right.setOnClickListener(onClickListener)
            iv_right.visibility = View.VISIBLE
        }
    }

    /**
     * 设置右侧为文本按钮
     *
     * @param btnText         btnText
     * @param onClickListener onClickListener
     */
    fun setRightText(btnText: String, onClickListener: View.OnClickListener) {
        tv_right.text = btnText
        tv_right.setOnClickListener(onClickListener)
        tv_right.visibility = View.VISIBLE
    }

    fun setRightTextColor(@ColorInt color: Int) {
        tv_right.setTextColor(color)
    }

    fun hideDividerView() {
        divider_view.visibility = View.GONE
    }
}
