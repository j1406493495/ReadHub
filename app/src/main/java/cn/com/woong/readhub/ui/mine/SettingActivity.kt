package cn.com.woong.readhub.ui.mine

import com.blankj.utilcode.util.SPUtils
import cn.com.woong.readhub.ui.widget.ArrowItemView
import com.blankj.utilcode.util.BarUtils
import android.content.Intent
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.com.woong.readhub.R
import cn.com.woong.readhub.constant.Constant
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        BarUtils.setStatusBarColor(this, resources.getColor(R.color.c1), 1)
        title_bar.setTitleBarBgColor(resources.getColor(R.color.c1))
        title_bar.setTitleColor(resources.getColor(R.color.b7))

        title_bar.setTitle(getString(R.string.setting))
        title_bar.setOnClickListener { finish() }

        arrow_english.setSwitchButtonVisible(true)
        arrow_english.setOnArrowListener(object : ArrowItemView.OnArrowListener {
            override fun onSwitchChange(checkable: Boolean) {
                SPUtils.getInstance().put(Constant.SP_SHOW_ENGLISH, checkable)
            }
        })
    }

    companion object {
        fun startSettingActivity(context: Context) {
            val intent = Intent(context, SettingActivity::class.java)
            context.startActivity(intent)
        }
    }
}
