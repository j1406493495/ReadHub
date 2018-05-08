package cn.com.woong.readhub.ui.mine.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.woong.readhub.R;
import cn.com.woong.readhub.base.BaseActivity;
import cn.com.woong.readhub.constant.Constant;
import cn.com.woong.readhub.ui.widget.ArrowItemView;
import cn.com.woong.readhub.ui.widget.TitleBarLayout;

/**
 * @author wong
 *         Created by wong on 2018/5/7.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.arrow_english)
    ArrowItemView arrowEnglish;

    public static void startSettingActivity(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.c1), 1);
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.c1));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));

        titleBar.setTitle(getString(R.string.setting));
        titleBar.setLeftBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrowEnglish.setSwitchButtonVisible(true);
    }

    @Override
    protected void initData() {
        arrowEnglish.setOnArrowListener(new ArrowItemView.OnArrowListener() {
            @Override
            public void onSwitchChange(boolean checkable) {
                SPUtils.getInstance().put(Constant.SP_SHOW_ENGLISH, checkable);
            }
        });
    }
}
