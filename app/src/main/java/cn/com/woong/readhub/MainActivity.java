package cn.com.woong.readhub;

import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import butterknife.BindView;
import cn.com.woong.readhub.base.BaseActivity;

/**
 * Created by wong on 2018/3/7.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rb_tab_hot)
    RadioButton rbTabHot;
    @BindView(R.id.rb_tab_weke)
    RadioButton rbTabWeke;
    @BindView(R.id.rb_tab_chat)
    RadioButton rbTabChat;
    @BindView(R.id.rb_tab_mine)
    RadioButton rbTabMine;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }
}
