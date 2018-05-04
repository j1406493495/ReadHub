package cn.com.woong.readhub.ui.mine.about;

import android.app.Activity;
import android.content.Intent;

import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;

/**
 * @author wong
 * Created by wong on 2018/4/29.
 */
public class AboutActivity extends BaseActivity {

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

    }

    @Override
    protected void initData() {

    }
}
