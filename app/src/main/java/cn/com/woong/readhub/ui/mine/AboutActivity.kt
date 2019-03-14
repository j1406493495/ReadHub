package cn.com.woong.readhub.ui.mine

import cn.com.woong.readhub.ui.WebActivity
import com.blankj.utilcode.util.BarUtils
import android.content.Intent
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.com.woong.readhub.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        initView()
    }

    private fun initView() {
        BarUtils.setStatusBarColor(this, resources.getColor(R.color.c1), 1)
        title_bar.setTitleBarBgColor(resources.getColor(R.color.c1))
        title_bar.setTitleColor(resources.getColor(R.color.b7))

        title_bar.setTitle(getString(R.string.about))
        title_bar.setOnClickListener { finish() }

        tv_blog_url.setOnClickListener {
            WebActivity.startWebActivity(this, tv_blog_url.text.toString())
        }

        tv_github_url.setOnClickListener {
            WebActivity.startWebActivity(this, tv_github_url.text.toString())
        }
    }

    companion object {
        fun startAboutActivity(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }
}
