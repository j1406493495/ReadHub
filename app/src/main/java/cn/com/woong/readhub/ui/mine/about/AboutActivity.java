package cn.com.woong.readhub.ui.mine.about;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.ui.WebActivity;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;

/**
 * @author wong
 * Created by wong on 2018/4/29.
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.tv_blog_url)
    TextView tvBlogUrl;
    @BindView(R.id.tv_github_url)
    TextView tvGithubUrl;

    public static void startAboutActivity(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.c1), 1);
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.c1));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));

        titleBar.setTitle(getString(R.string.about));
        titleBar.setLeftBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_blog_url, R.id.tv_github_url})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_blog_url:
                WebActivity.startWebActivity(this, tvBlogUrl.getText().toString());
                break;
            case R.id.tv_github_url:
                WebActivity.startWebActivity(this, tvGithubUrl.getText().toString());
                break;
            default:
                break;
        }
    }

}
