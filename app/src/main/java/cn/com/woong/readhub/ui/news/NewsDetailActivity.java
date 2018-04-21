package cn.com.woong.readhub.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.BarUtils;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;

/**
 * Created by wong on 2018/4/20.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.fl_news_detail)
    FrameLayout flNewsDetail;

    private static final String WEB_URL = "web_url";
    private String mWebUrl;

    AgentWeb mAgentWeb;

    public static void startNewsDetailActivity(Context context, String url) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(WEB_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarVisibility(this, false);
    }

    @Override
    protected void initData() {
        mWebUrl = getIntent().getStringExtra(WEB_URL);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(flNewsDetail, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mWebUrl);
    }
}
