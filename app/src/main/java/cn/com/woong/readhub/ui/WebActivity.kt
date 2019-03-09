package cn.com.woong.readhub.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.com.woong.readhub.R
import com.blankj.utilcode.util.BarUtils
import com.just.agentweb.AgentWeb
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_web.*
import android.content.Intent

class WebActivity : AppCompatActivity() {
    private val WEB_URL = "web_url"
    private var mWebUrl: String? = null

    var mAgentWeb: AgentWeb? = null

    fun startWebActivity(context: Context, url: String) {
        val intent = Intent(context, WebActivity::class.java)
        intent.putExtra(WEB_URL, url)
        context.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        BarUtils.setStatusBarVisibility(this, false)

        mWebUrl = intent.getStringExtra(WEB_URL)

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(fl_web, FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mWebUrl)
    }
}